package com.fin.moblibrary.repository;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.BookRecord;
import com.fin.moblibrary.model.BookRecordMultiKeysClass;
import com.fin.moblibrary.view.ViewBookRecord;
import com.fin.moblibrary.view.ViewHotBook;

public interface BookRecordCrudRepository extends PagingAndSortingRepository<BookRecord, BookRecordMultiKeysClass>,CrudRepository<BookRecord, BookRecordMultiKeysClass>{

	public BookRecord findByBookIdOrderByBorrowTimestampDesc(Integer bookId);
	
	@Modifying
	@Query("update BookRecord set returnTimestamp = :returnTimestamp where bookId =:bookId and borrowTimestamp =:borrowTimestamp")
	public void updateReturnTimestamp(@Param("returnTimestamp")Timestamp returnTimestamp,@Param("bookId")Integer bookId,@Param("borrowTimestamp")Timestamp borrowTimestamp);

//	@Query("SELECT new com.fin.moblibrary.view.ViewBookRecord(libraray,book,bookRecord) FROM "
//	        + "com.fin.moblibrary.domain.Library libraray, com.fin.moblibrary.domain.Book book, BookRecord bookRecord "
//	        + "WHERE bookRecord.accountId = :accountId and bookRecord.bookId = book.id and bookRecord.libraryId = libraray.id "
//	        + "order by bookRecord.returnDate DESC) ")
//	ViewBookRecord[] findViewBookRecordByAccountId(Integer accountId);
//	
//	@Query("SELECT new com.fin.moblibrary.view.ViewBookRecord(libraray,book,bookRecord) FROM "
//	        + "com.fin.moblibrary.domain.Library libraray, com.fin.moblibrary.domain.Book book, BookRecord bookRecord "
//	        + "WHERE bookRecord.accountId = :accountId and bookRecord.borrow = :borrow "
//	        + "and bookRecord.bookId = book.id and bookRecord.libraryId = libraray.id "
//	        + "order by bookRecord.returnDate ASC) ")
//	ViewBookRecord[] findViewBookRecordByAccountIdAndBorrow(Integer accountId,boolean borrow);
//
//	@Query("SELECT new com.fin.moblibrary.view.ViewHotBook(bookRecord,bookCategory,book) FROM "
//	        + "com.fin.moblibrary.domain.Book book, com.fin.moblibrary.domain.BookCategory bookCategory, BookRecord bookRecord "
//	        + "WHERE bookRecord.bookId = book.id and book.bookCategoryId = bookCategory.id and rowNum<=10"
//	        + "group by book.bookCategoryId order by count(*) DESC")
//	ViewHotBook[] findViewHotBooks();

	public BookRecord[] findByAccountIdAndBorrow(Integer accountId, boolean borrow);

	
}
