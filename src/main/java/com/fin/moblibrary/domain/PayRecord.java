package com.fin.moblibrary.domain;

import java.util.Date;

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
	private Date date;
	private String detail;
	
	public PayRecord() {
		// TODO Auto-generated constructor stub
	}
	
	public PayRecord(Integer accountId,Boolean save,double amount,double balance,Date date,String detail) {
		this.accountId = accountId;
		this.balance = balance;
		this.save = save;
		this.amount = amount;
		this.date = date;
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
	@Column( nullable = false )
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column( length = 20, nullable = false )
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
