package com.fin.moblibrary.view;

import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.BookRecord;

public class ViewHotBook {
	private BookRecord bookRecord;
	private BookCategory bookCategory;
	private Book book;
	
	public ViewHotBook() {
		
	}
	
	public ViewHotBook(BookRecord bookRecord,BookCategory bookCategory,Book book) {
		this.bookRecord = bookRecord;
		this.book = book;
		this.bookCategory = bookCategory;
	}
	
	public BookRecord getBookRecord() {
		return bookRecord;
	}
	public void setBookRecord(BookRecord bookRecord) {
		this.bookRecord = bookRecord;
	}
	
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
