package com.fin.moblibrary.view;

import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookRecord;
import com.fin.moblibrary.domain.Library;

/**
 * @author KatherineJY
 * 借还书记录视图
 * 含有BookRecord、Book、Library中的部分属性
 */
public class ViewBookRecord {
	private Library library;
	private Book book;
	private BookRecord bookRecord;
	
	public ViewBookRecord() {
		
	}
	
	public ViewBookRecord(Library library,Book book,BookRecord bookRecord) {
		this.book = book;
		this.bookRecord = bookRecord;
		this.library = library;
	}
	
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public BookRecord getBookRecord() {
		return bookRecord;
	}
	public void setBookRecord(BookRecord bookRecord) {
		this.bookRecord = bookRecord;
	}
	
}
