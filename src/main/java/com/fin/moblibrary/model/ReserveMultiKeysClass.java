package com.fin.moblibrary.model;

import java.io.Serializable;

/**
 * @author KatherineJY
 * Reserve类的复合主键
 * @param account_id
 * @param book_category_id
 * @param library_id
 */
public class ReserveMultiKeysClass implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer accountId;
	private Integer bookCategoryId;
	private Integer libraryId;
	
	public ReserveMultiKeysClass() {
		
	}
	
	public ReserveMultiKeysClass(Integer accountId, Integer bookCategoryId, Integer libraryId) {
		this.accountId = accountId;
		this.bookCategoryId = bookCategoryId;
		this.libraryId = libraryId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(Integer bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}


	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = PRIME * result + ((bookCategoryId == null) ? 0 : bookCategoryId.hashCode());
		result = PRIME * result + ((libraryId == null) ? 0 : libraryId.hashCode());
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

		final ReserveMultiKeysClass other = (ReserveMultiKeysClass) obj;
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
		if (libraryId == null) {
			if (other.libraryId != null) {
				return false;
			}
		} else if (!libraryId.equals(other.libraryId)) {
			return false;
		}
		return true;
	}
}
