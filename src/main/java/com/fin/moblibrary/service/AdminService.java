package com.fin.moblibrary.service;

import java.util.Calendar;
import java.sql.Date;

import javax.sql.rowset.spi.SyncFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.BookCrudRepository;
import com.fin.moblibrary.repository.ReserveCrudRepository;

@Service
public class AdminService {

	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;
	@Autowired
	private ReserveCrudRepository reserveCrudRepository;
	@Autowired
	private BookCrudRepository bookCrudRepository;
	
	private Order orderByDateDesc = new Order(Sort.Direction.DESC, "date");
	private Order orderByDateAsc = new Order(Sort.Direction.ASC, "date");

	public ResponseWrapper addBookCategory(BookCategory bookCategory) {
		if (bookCategoryCrudRepository.findByNameAndAuthorAndPublisher(bookCategory.getName(), bookCategory.getAuthor(),
				bookCategory.getPublisher()) != null)
			return new ResponseWrapper(false,"the book has exists",null);
		bookCategoryCrudRepository.save(bookCategory);
		return new ResponseWrapper(true,"",null);
	}

	public synchronized ResponseWrapper handleEmpiredReserve() {
		java.util.Date curDate = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		Date date = new Date(calendar.getTime().getTime());
		Reserve[] reserves = reserveCrudRepository.findByExpire(date);
		for( Reserve reserve : reserves ) {
			Book book = bookCrudRepository.findAllByBookCategoryIdAndLibraryIdAndSave(reserve.getBookCategoryId(),reserve.getLibraryId(),true);
			if( book == null )
				return new ResponseWrapper(false,"can not find the reserve book",null);
			bookCrudRepository.updateIsSave(false, book.getId());
			//判断是否被预定
			Reserve reserveOther = reserveCrudRepository.findByBookCategoryIdAndLibraryIdAndExpireOrderByReserveTimestamp(book.getBookCategoryId(),book.getLibraryId(),null);
			if( reserveOther != null ) {
				bookCrudRepository.updateIsSave(true, book.getId());
				//更新他人的预定状态
				Date expire = getFiveDaysNext(date);
				reserveCrudRepository.updateReserveDateAndExpire(date, expire, reserveOther.getAccountId(), book.getBookCategoryId());
			}
		}
		return null;
	}
	
	private Date getFiveDaysNext(Date curDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.DATE, 5);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

}
