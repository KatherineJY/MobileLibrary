package com.fin.moblibrary.model;

import java.io.Serializable;

/**
 * @author KatherineJY
 * Review类的复合主键
 * @param account_id
 * @param book_category_id
 */
public class ReviewMultiKeysClass implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer bookCategoryId;
	private Integer accountId;
	
	public ReviewMultiKeysClass() {
		
	}
	
	public ReviewMultiKeysClass(Integer accountId, Integer bookCategoryId) {
		this.accountId = accountId;
		this.bookCategoryId = bookCategoryId;
	}

	public Integer getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(Integer bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = PRIME * result + ((bookCategoryId == null) ? 0 : bookCategoryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final ReviewMultiKeysClass other = (ReviewMultiKeysClass) obj;
		if (accountId == null) {
			if (other.accountId != null) {
				return false;
			}
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (bookCategoryId == null) {
			if (other.bookCategoryId != null) {
				return false;
			}
		} else if (!bookCategoryId.equals(other.bookCategoryId)) {
			return false;
		}
		return true;
	}
}
