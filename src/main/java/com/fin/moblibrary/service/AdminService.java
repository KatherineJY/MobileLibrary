package com.fin.moblibrary.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.BookCrudRepository;
import com.fin.moblibrary.repository.ReserveCrudRepository;

@Service
public class AdminService {

	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;
	@Autowired
	private ReserveCrudRepository reserveCrudRepository;
	@Autowired
	private BookCrudRepository bookCrudRepository;
	
	private Order orderByDateDesc = new Order(Sort.Direction.DESC, "date");
	private Order orderByDateAsc = new Order(Sort.Direction.ASC, "date");

	public ResponseWrapper addBookCategory(BookCategory bookCategory) {
		if (bookCategoryCrudRepository.findByNameAndAuthorAndPublisher(bookCategory.getName(), bookCategory.getAuthor(),
				bookCategory.getPublisher()) != null)
			return new ResponseWrapper(false,"the book has exists",null);
		bookCategoryCrudRepository.save(bookCategory);
		return new ResponseWrapper(true,"",null);
	}

	public ResponseWrapper handleEmpiredReserve() {
		Date curDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		curDate = calendar.getTime();
		Reserve[] reserves = reserveCrudRepository.findByExpire(curDate);
		for( Reserve reserve : reserves ) {
			Book book = bookCrudRepository.findAllByBookCategoryIdAndLibraryIdAndSave(reserve.getBookCategoryId(),reserve.getLibraryId(),true);
			if( book == null )
				return new ResponseWrapper(false,"can not find the reserve book",null);
			bookCrudRepository.updateIsSave(false, book.getId());
			//璇ュ浘涔︽槸鍚﹁棰勫畾
			Reserve reserveOther = reserveCrudRepository.findByBookCategoryIdAndLibraryIdAndExpire(book.getBookCategoryId(),book.getLibraryId(),null,orderByDateAsc);
			if( reserveOther != null ) {
				bookCrudRepository.updateIsSave(true, book.getId());
				// 鏇存敼棰勮鍗曠殑鐘舵��
				Date expire = getFiveDaysNext(curDate);
				reserveCrudRepository.updateReserveDateAndExpire(curDate, expire, reserveOther.getAccountId(), book.getBookCategoryId());
			}
		}
		return null;
	}
	
	private Date getFiveDaysNext(Date curDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.DATE, 5);
		return calendar.getTime();
	}

}
