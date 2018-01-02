package com.fin.moblibrary.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author KatherineJY 
 * BookRecord的复合主键类
 * @Param bookId
 * @Param borrowDate
 */
public class BookRecordMultiKeysClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Timestamp borrowTimestamp;
	private Integer bookId;

	public BookRecordMultiKeysClass() {
	}

	public BookRecordMultiKeysClass(Timestamp borrowTimestamp, Integer bookId) {
		this.borrowTimestamp = borrowTimestamp;
		this.bookId = bookId;
	}

	public Timestamp getBorrowTimestamp() {
		return borrowTimestamp;
	}

	public void setBorrowTimestamp(Timestamp borrowTimestamp) {
		this.borrowTimestamp = borrowTimestamp;
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
		result = PRIME * result + ((borrowTimestamp == null) ? 0 : borrowTimestamp.hashCode());
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
		if (borrowTimestamp == null) {
			if (other.borrowTimestamp != null) {
				return false;
			}
		} else if (!borrowTimestamp.equals(other.borrowTimestamp)) {
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
