package com.fin.moblibrary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fin.moblibrary.model.ReviewMultiKeysClass;

/**
 * @author KatherineJY
 * Review
 * 读者对于某本书的书评
 */
@Entity
@IdClass(ReviewMultiKeysClass.class)
public class Review {
	private Integer bookCategoryId;
	private String content;
	private Integer accountId;
	
	@Id
	public Integer getBookCategoryId() {
		return bookCategoryId;
	}
	public void setBookCategoryId(Integer bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	
	@Id
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	@Column( length = 280 )
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
