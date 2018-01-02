package com.fin.moblibrary.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author KatherineJY PayRecord的复合主键类
 * @param account_id
 * @param date
 */
public class PayRecordMultiKeysClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer accountId;
	private Timestamp timestamp;

	public PayRecordMultiKeysClass() {

	}

	public PayRecordMultiKeysClass(Integer accountId, Timestamp timestamp) {
		this.accountId = accountId;
		this.timestamp = timestamp;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = PRIME * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}
}
