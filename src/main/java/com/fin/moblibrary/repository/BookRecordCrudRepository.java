package com.fin.moblibrary.repository;

import java.util.Date;
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

	public BookRecord findByBookId(Integer bookId, Order order);
	
	@Modifying
	@Query("update BookRecord set returnDate = :returnDate where bookId =:bookId and borrowDate =:borrowDate")
	public void updateReturnDate(@Param("returnDate")Date returnDate,@Param("bookId")Integer bookId,@Param("borrowDate")Date borrowDate);

	@Query("SELECT ViewBookRecord(libraray,book,bookRecord))FROM "
	        + "com.fin.moblibrary.domain.Library libraray, com.fin.moblibrary.domain.Book book, BookRecorder bookRecorder "
	        + "WHERE bookRecorder.accountId = :accountId and bookRecorder.bookId = Book.bookId and bookRecorder.libraryId = libraray.libraryId "
	        + "order by bookRecord.returnDate DESC) ")
	ViewBookRecord[] findViewBookRecordByAccountId(Integer accountId);
	
	@Query("SELECT ViewBookRecord(libraray,book,bookRecord))FROM "
	        + "com.fin.moblibrary.domain.Library libraray, com.fin.moblibrary.domain.Book book, BookRecorder bookRecorder "
	        + "WHERE bookRecorder.accountId = :accountId and bookRecorder.borrow = borrow "
	        + "and bookRecorder.bookId = Book.bookId and bookRecorder.libraryId = libraray.libraryId "
	        + "order by bookRecord.returnDate ASC) ")
	ViewBookRecord[] findViewBookRecordByAccountIdAndBorrow(Integer accountId,boolean borrow);

	@Query("SELECT ViewHotBook(bookRecord,book,bookCategory))FROM "
	        + "com.fin.moblibrary.domain.Book book, com.fin.moblibrary.domain.BookCategory book Category, BookRecorder bookRecorder "
	        + "WHERE bookRecorder.bookId = Book.bookId and Book.categoryId = BookCategory.categoryId and rowNum<=10"
	        + "group by Book.categoryId order by count(*) DESC")
	ViewHotBook[] findViewHotBooks();

	public BookRecord[] findByAccountIdAndBorrow(Integer accountId, boolean borrow);

	
}
