package com.fin.moblibrary.domain;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author KatherineJY
 * Category图书的类别
 */
public class Category {
	private String sort;
	private char id;
	
	@Column( length = 20 )
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Id
	public char getId() {
		return id;
	}
	public void setId(char id) {
		this.id = id;
	}
	
	
}
