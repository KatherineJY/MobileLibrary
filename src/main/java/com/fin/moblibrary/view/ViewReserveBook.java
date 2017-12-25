package com.fin.moblibrary.view;

import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Reserve;

public class ViewReserveBook {
	private Reserve reserve;
	private BookCategory bookCategory;
	
	public Reserve getReserve() {
		return reserve;
	}
	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}
	
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	
}
