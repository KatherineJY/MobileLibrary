package com.fin.moblibrary.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fin.moblibrary.model.PayRecordMultiKeysClass;

/**
 * @author KatherineJY
 * PayRecord
 * 用户的资金使用情况
 */
@Entity
@IdClass( PayRecordMultiKeysClass.class )
public class PayRecord {
	private Integer accountId;
	private boolean save;
	private double amount;
	private double balance;
	private Timestamp timestamp;

	private String detail;
	
	public PayRecord() {
		// TODO Auto-generated constructor stub
	}
	
	public PayRecord(Integer accountId,boolean save,double amount,double balance,Timestamp timestamp,String detail) {
		this.accountId = accountId;
		this.balance = balance;
		this.save = save;
		this.amount = amount;
		this.timestamp = timestamp;
		this.detail = detail;
	}
	
	@Id
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	@Column( nullable = false )
	public boolean isSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
	
	@Column( nullable = false )
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column( nullable = false )
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Id
	@Column( nullable = false, name="payDate" )
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Column( length = 40, nullable = false )
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
