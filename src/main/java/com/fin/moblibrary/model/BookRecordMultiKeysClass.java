package com.fin.moblibrary.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author KatherineJY 
 * BookRecord的复合主键类
 * @Param account_id
 * @Param borrowDate
 */
public class BookRecordMultiKeysClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date borrowDate;
	private Integer book_id;

	public BookRecordMultiKeysClass() {
	}

	public BookRecordMultiKeysClass(Date borrowDate, Integer book_id) {
		this.borrowDate = borrowDate;
		this.book_id = book_id;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((borrowDate == null) ? 0 : borrowDate.hashCode());
		result = PRIME * result + ((book_id == null) ? 0 : book_id.hashCode());
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

		final BookRecordMultiKeysClass other = (BookRecordMultiKeysClass) obj;
		if (borrowDate == null) {
			if (other.borrowDate != null) {
				return false;
			}
		} else if (!borrowDate.equals(other.borrowDate)) {
			return false;
		}
		if (book_id == null) {
			if (other.book_id != null) {
				return false;
			}
		} else if (!book_id.equals(other.book_id)) {
			return false;
		}
		return true;
	}

}
