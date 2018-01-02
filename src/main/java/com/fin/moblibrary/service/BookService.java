package com.fin.moblibrary.service;

import java.util.Calendar;
import java.sql.Date;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.BookRecord;
import com.fin.moblibrary.domain.PayRecord;
import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.BookCrudRepository;
import com.fin.moblibrary.repository.BookRecordCrudRepository;
import com.fin.moblibrary.repository.PayRecordCrudRepository;
import com.fin.moblibrary.repository.ReserveCrudRepository;
import com.fin.moblibrary.view.ViewBookInfo;

/**
 * @author KatherineJY 处理与书籍相关的事件
 */

@Service
public class BookService {

	@Autowired
	private AccountCrudRepository accountCrudRepository;
	@Autowired
	private BookRecordCrudRepository bookRecordCrudRepository;
	@Autowired
	private BookCrudRepository bookCrudRepository;
	@Autowired
	private ReserveCrudRepository reserveCrudRepository;
	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;
	@Autowired
	private PayRecordCrudRepository payRecordCrudRepository;

	private Order orderByDateDesc = new Order(Sort.Direction.DESC, "date");
	private Order orderByDateAsc = new Order(Sort.Direction.ASC, "date");
	
	/**
	 * 显示特定的一本书的所有书的信息
	 * @param bookId
	 * @return bookCategory + books
	 * */
	public ResponseWrapper showDetailBook(Integer bookCategoryId) {
		BookCategory bookCategory = bookCategoryCrudRepository.findOne(bookCategoryId);
		if( bookCategory == null )
			return new ResponseWrapper(false,"the book isn't exist",null);
		//ViewBookInfo[] viewBookInfos = bookCrudRepository.findAllViewBookInfoByBookCategoryIdAndBorrow(bookCategoryId,false);
		//return new ResponseWrapper(true,"",viewBookInfos);
		return null;
	}
	
	/**
	 * 还书
	 * @param accountId
	 * @param bookId
	 * @param newLibraryId
	 * */
	@Transactional
	public synchronized ResponseWrapper returnBook(Integer accountId,Integer bookId,Integer newLibraryId) {
		Account account = accountCrudRepository.findOne(accountId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		Book book = bookCrudRepository.findOne(bookId);
		System.out.println("*********************1*****************");

		if( book == null )
			return new ResponseWrapper(false,"book isn't exist",null);
		BookRecord bookRecord = bookRecordCrudRepository.findByBookIdOrderByBorrowTimestampDesc(bookId);
		System.out.println("**********************2****************");
		if( bookRecord == null )
			return new ResponseWrapper(false,"hadn't borrowed the book",null);
		//更新借还书记录 填写还书日期
		java.util.Date curDate_java = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate_java);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		curDate_java = calendar.getTime();
		Date curDate = new Date(curDate_java.getTime());
		Timestamp curtimestamp = new Timestamp(curDate.getTime());
		bookRecordCrudRepository.updateReturnTimestamp(curtimestamp,bookId,bookRecord.getBorrowTimestamp());
		//更新图书的位置以及借还书状态
		bookCrudRepository.updatePosition(newLibraryId,bookId);
		bookCrudRepository.updateIsBorrow(false, bookId);
		//该图书是否被预定
		Reserve reserve = reserveCrudRepository.findByBookCategoryIdAndLibraryIdAndExpireOrderByReserveTimestamp(book.getBookCategoryId(),newLibraryId,null);
		if( reserve != null ) {
			bookCrudRepository.updateIsSave(true, bookId);
			// 更改预订单的状态
			Date expire = getFiveDaysNext(curDate);
			reserveCrudRepository.updateReserveDateAndExpire(curDate, expire, accountId,book.getBookCategoryId());
		}
		
		//扣费
		Date borrowDate = new Date( bookRecord.getBorrowTimestamp().getTime());
		int borrowDays = daysBetween(curDate,borrowDate);
		double amount = countMoney(borrowDays);
		double balance = account.getBalance() - amount;
		accountCrudRepository.updateBalance( balance, accountId);
		//更新流水记录
		String detail = "Return book " + bookCategoryCrudRepository.findOne(book.getBookCategoryId()).getName() 
				+ ". This book has been borrowed for " + borrowDays 
				+ "days. It costs "+amount +" yuan. Balance is "+ balance+" yuan.";
		Timestamp curTimestamp = new Timestamp(curDate.getTime());
		PayRecord payRecord = new PayRecord(accountId, false, amount, balance, curTimestamp, detail);
		payRecordCrudRepository.save(payRecord);
		return new ResponseWrapper(true,"",balance);
	}

	/**
	 * 借书
	 * @param bookId
	 * @param accountId
	 * */
	@Transactional
	public synchronized ResponseWrapper borrowBook(Integer accountId, Integer bookId) {
		Account account = accountCrudRepository.findOne(accountId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		//是否交纳押金
		if( account.isDeposit()==false )
			return new ResponseWrapper(false,"deposit first",null);
		//余额是否为正
		if( account.getBalance() < 0 )
			return new ResponseWrapper(false,"without enough money",null);
		//借书是否达到上限
		BookRecord[] bookRecords = bookRecordCrudRepository.findByAccountIdAndBorrow(accountId, true);
		if( bookRecords.length >= 3 )
			return new ResponseWrapper(false,"you have borrow three books",null);
		//判断是否有逾期的书籍
		java.util.Date curDate = new java.util.Date();
		Date date = new Date(curDate.getTime());
		for( BookRecord bookRecord : bookRecords ) {
			Date borrowdate = new Date(bookRecord.getBorrowTimestamp().getTime());
			int days = daysBetween(borrowdate, date);
			if( days > 30 )
				return new ResponseWrapper(false,"you should return the book which you haved borrowed for more than 30 days first",null);
		}
		Book book = bookCrudRepository.findOne(bookId);
		if( book == null )
			return new ResponseWrapper(false,"the book isn't exist",null);
		if( book.isBorrow() == true )
			return new ResponseWrapper(false,"the book has been borrowed",null);
		//借阅图书
		bookCrudRepository.updateIsBorrow(true, bookId);
		//增加借还书记录
		Timestamp timestamp = new Timestamp(date.getTime());
		BookRecord bookRecordNew = new BookRecord(accountId, bookId, book.getLibraryId(), true, timestamp, null);
		bookRecordCrudRepository.save(bookRecordNew);
		//判读是否需要更新预定表
		Reserve reserve = reserveCrudRepository.findByAccountIdAndBookCategoryId(accountId, book.getBookCategoryId());
		if( reserve!=null )
			reserveCrudRepository.delete(reserve);
		return null;
	}

	private Date getFiveDaysNext(Date curDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.DATE, 5);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

	private int daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return (int) Math.abs(difference);
	}

	private double countMoney(int borrowDays) {
		if( borrowDays <= 5) 
			return borrowDays*0.1;
		else if ( borrowDays <=10 )
			return 0.5 + (borrowDays-5)*0.3;
		else if ( borrowDays <= 30 )
			return 0.5 + 1.5 + (borrowDays-10)*0.5;
		else return 0.5 + 1.5 + 2.5 + (borrowDays-30)*0.3;
	}

}
