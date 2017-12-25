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
	
	private Integer account_id;
	private Integer book_category_id;
	private Integer library_id;
	
	public ReserveMultiKeysClass() {
		
	}
	
	public ReserveMultiKeysClass(Integer account_id, Integer book_category_id, Integer library_id) {
		this.account_id = account_id;
		this.book_category_id = book_category_id;
		this.library_id = library_id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getBook_category_id() {
		return book_category_id;
	}

	public void setBook_category_id(Integer book_category_id) {
		this.book_category_id = book_category_id;
	}

	public Integer getLibrary_id() {
		return library_id;
	}

	public void setLibrary_id(Integer library_id) {
		this.library_id = library_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((account_id == null) ? 0 : account_id.hashCode());
		result = PRIME * result + ((book_category_id == null) ? 0 : book_category_id.hashCode());
		result = PRIME * result + ((library_id == null) ? 0 : library_id.hashCode());
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
		if (account_id == null) {
			if (other.account_id != null) {
				return false;
			}
		} else if (!account_id.equals(other.account_id)) {
			return false;
		}
		if (book_category_id == null) {
			if (other.book_category_id != null) {
				return false;
			}
		} else if (!book_category_id.equals(other.book_category_id)) {
			return false;
		}
		if (library_id == null) {
			if (other.library_id != null) {
				return false;
			}
		} else if (!library_id.equals(other.library_id)) {
			return false;
		}
		return true;
	}
}
