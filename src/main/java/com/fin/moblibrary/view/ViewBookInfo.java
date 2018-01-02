package com.fin.moblibrary.view;

import java.io.Serializable;

import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Library;

public class ViewBookInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book;
	private Library library;
	private BookCategory bookCategory;
	
	public ViewBookInfo() {
		
	}
	
	public ViewBookInfo(Book book,Library library,BookCategory bookCategory) {
		this.book = book;
		this.library = library;
		this.bookCategory = bookCategory;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	
}
