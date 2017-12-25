package com.fin.moblibrary.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fin.moblibrary.model.BookRecordMultiKeysClass;

/**
 * @author KatherineJY
 * BookRecord
 * 记录用户借还情况
 */
@Entity
@IdClass(BookRecordMultiKeysClass.class)
public class BookRecord {
	private Integer accountId;
	private Integer bookId;
	private Integer libraryId;
	private boolean borrow;
	private Date borrowDate;
	private Date returnDate;
	
	public BookRecord() {
		
	}
	
	public BookRecord(Integer accountId,Integer bookId,Integer libraryId,boolean borrow,Date borrowDate,Date returnDate) {
		this.accountId = accountId;
		this.bookId = bookId;
		this.libraryId = libraryId;
		this.borrow = borrow;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	@Id
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public Integer getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}
	
	public boolean isBorrow() {
		return borrow;
	}
	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	
	@Id
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
}
