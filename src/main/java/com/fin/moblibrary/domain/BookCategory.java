package com.fin.moblibrary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author KatherineJY
 * BookCategory类
 * 表示的是由书名、作者、出版社唯一确定的一本书，可能有多本
 */
@Entity
public class BookCategory {
	
	private Integer id;
	private String name;
	private String author;
	private String publisher;
	private char categoryId;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column( length = 40 )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column( length = 40 )
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column( length = 40 )
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public char getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(char categoryId) {
		this.categoryId = categoryId;
	}
	

	
}
