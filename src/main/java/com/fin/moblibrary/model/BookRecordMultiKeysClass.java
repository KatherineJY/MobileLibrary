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
	private Integer bookId;

	public BookRecordMultiKeysClass() {
	}

	public BookRecordMultiKeysClass(Date borrowDate, Integer bookId) {
		this.borrowDate = borrowDate;
		this.bookId = bookId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((borrowDate == null) ? 0 : borrowDate.hashCode());
		result = PRIME * result + ((bookId == null) ? 0 : bookId.hashCode());
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
		if (bookId == null) {
			if (other.bookId != null) {
				return false;
			}
		} else if (!bookId.equals(other.bookId)) {
			return false;
		}
		return true;
	}

}
