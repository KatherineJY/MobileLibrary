package com.fin.moblibrary.service;


import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.LibrarayCrudRepository;
import com.fin.moblibrary.repository.ReserveCrudRepository;
import com.fin.moblibrary.view.ViewReserveBook;

@Service
public class ReserveService {
	
	@Autowired
	private ReserveCrudRepository reserveCrudRepository;
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	@Autowired
	private LibrarayCrudRepository librarayCrudRepository;
	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;
	
	/**
	 * 查看预定图书
	 * @param accountId
	 * */
	public ResponseWrapper showResereveBook(Integer accountId) {
		Account account = accountCrudRepository.findOne(accountId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		ViewReserveBook[] viewReserveBooks = reserveCrudRepository.findViewReserveBookByAccountId(accountId);
		return new ResponseWrapper(true,"",viewReserveBooks);
	}

	/**
	 * 预定图书
	 * @param accountId
	 * @param bookCategoryId
	 * @param libraryId
	 * */
	public synchronized ResponseWrapper reserveBook(Integer accountId, Integer bookCategoryId,Integer libraryId) {
		Account account = accountCrudRepository.findOne(accountId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		if( bookCategoryCrudRepository.findOne(bookCategoryId) == null )
			return new ResponseWrapper(false, "books isn't exist", null);
		if( librarayCrudRepository.findOne(libraryId) == null )
			return new ResponseWrapper(false, "library isn't exist",null);
		Reserve reserve = reserveCrudRepository.findByAccountIdAndBookCategoryId(accountId,bookCategoryId);
		//如果这个用户预定过这本书，且这本书尚在预定等待中，或是在可借阅状态，则预定失败
		if( reserve!=null && ( reserve.getExpire()==null || reserve.getExpire().after(new java.util.Date()) ) )
			return new ResponseWrapper(false, "you have ordered the book and your reservation is still validate",null);

		java.util.Date datePre = new java.util.Date();
		Timestamp timestamp = new Timestamp(datePre.getTime());
		Reserve  reserveNew = new Reserve(accountId, bookCategoryId, libraryId, timestamp, null);
		reserveCrudRepository.save(reserveNew);
		return new ResponseWrapper(true,"",null);
	}
	
	/**
	 * 取消图书预定
	 * @param accountId
	 * @param bookCategoryId
	 * */
	public synchronized ResponseWrapper cancelReserve(Integer accountId, Integer bookCategoryId) {
		if( accountCrudRepository.findOne(accountId) == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		if( bookCategoryCrudRepository.findOne(bookCategoryId) == null )
			return new ResponseWrapper(false, "books isn't exist", null);
		Reserve reserve = reserveCrudRepository.findByAccountIdAndBookCategoryId(accountId, bookCategoryId);
		if( reserve!=null )
			reserveCrudRepository.delete(reserve);
		return new ResponseWrapper(true,"",null);
	}
	
}
