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

	private Integer account_id;
	private Date date;

	public PayRecordMultiKeysClass() {

	}

	public PayRecordMultiKeysClass(Integer account_id, Date date) {
		this.account_id = account_id;
		this.date = date;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
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
		result = PRIME * result + ((account_id == null) ? 0 : account_id.hashCode());
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
		if (account_id == null) {
			if (other.account_id != null) {
				return false;
			}
		} else if (!account_id.equals(other.account_id)) {
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
