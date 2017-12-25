package com.fin.moblibrary.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author KatherineJY PayRecord的复合主键类
 * @param account_id
 * @param date
 */
public class PayRecordMultiKeysClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer accountId;
	private Date date;

	public PayRecordMultiKeysClass() {

	}

	public PayRecordMultiKeysClass(Integer accountId, Date date) {
		this.accountId = accountId;
		this.date = date;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = PRIME * result + ((date == null) ? 0 : date.hashCode());
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

		final PayRecordMultiKeysClass other = (PayRecordMultiKeysClass) obj;
		if (accountId == null) {
			if (other.accountId != null) {
				return false;
			}
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		return true;
	}
}
