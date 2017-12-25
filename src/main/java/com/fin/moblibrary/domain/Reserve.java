package com.fin.moblibrary.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fin.moblibrary.model.ReserveMultiKeysClass;

/**
 * @author KatherineJY
 * Reserve类
 * 记录用户预定了哪个图书馆的哪本书
 */
@Entity
@IdClass(ReserveMultiKeysClass.class)
public class Reserve {
	private Integer accountId;
	private Integer bookCategoryId;
	private Integer libraryId;
	private Date reserveDate;
	private Date expire;
	
	public Reserve() {
		
	}
	
	public Reserve(Integer accountId,Integer bookCategoryId,Integer libraryId,Date date,Date expire) {
		this.accountId = accountId;
		this.bookCategoryId = bookCategoryId;
		this.libraryId = libraryId;
		this.reserveDate = date;
		this.expire = expire;
	}
	
	@Id
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	@Id
	public Integer getBookCategoryId() {
		return bookCategoryId;
	}
	public void setBookCategoryId(Integer bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	
	@Id
	public Integer getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}
	
	@Column( nullable = false )
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	
	@Column( nullable = false )
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
}
