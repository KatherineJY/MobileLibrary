package com.fin.moblibrary.domain;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
	private Timestamp borrowTimestamp;
	private Timestamp returnTimestamp;
	
	public BookRecord() {
		
	}
	
	public BookRecord(Integer accountId,Integer bookId,Integer libraryId,boolean borrow,Timestamp borrowTimestamp,Timestamp returnTimestamp) {
		this.accountId = accountId;
		this.bookId = bookId;
		this.libraryId = libraryId;
		this.borrow = borrow;
		this.borrowTimestamp = borrowTimestamp;
		this.returnTimestamp = returnTimestamp;
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
	public Timestamp getBorrowTimestamp() {
		return borrowTimestamp;
	}
	public void setBorrowTimestamp(Timestamp borrowTimestamp) {
		this.borrowTimestamp = borrowTimestamp;
	}
	
	public Timestamp getReturnTimestamp() {
		return returnTimestamp;
	}
	public void setReturnTimestamp(Timestamp returnTimestamp) {
		this.returnTimestamp = returnTimestamp;
	}
	
}
