package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Book;
import com.fin.moblibrary.view.ViewBookInfo;

public interface BookCrudRepository extends CrudRepository<Book, Integer>{
	
	@Modifying
	@Query("update Book set libraryId = :libraryId where id =:id")
	public void updatePosition(@Param("libraryId")Integer libraryId,@Param("id")Integer id);
	
	@Modifying
	@Query("update Book set borrow = :borrow where id =:id")
	public void updateIsBorrow(@Param("borrow")boolean borrow,@Param("id")Integer id);
	
	@Modifying
	@Query("update Book set save = :save where id =:id")
	public void updateIsSave(@Param("save")boolean save,@Param("id")Integer id);


	@Query("SELECT new com.fin.moblibrary.view.ViewBookInfo(book,library,bookCategory) FROM "
	        + "com.fin.moblibrary.domain.Library library, Book book, com.fin.moblibrary.domain.BookCategory bookCategory "
	        + "WHERE book.bookCategoryId = :bookCategoryId and book.borrow = :borrow "
	        + "and bookCategory.id=book.bookCategoryId and book.libraryId = library.id "
	        )
	public ViewBookInfo[] findAllViewBookInfoByBookCategoryIdAndBorrow(@Param("bookCategoryId")Integer bookCategoryId, @Param("borrow")boolean borrow);
	
	public Book[] findAllByBookCategoryIdAndBorrow(Integer bookCategoryId, boolean borrow);

	public Book findAllByBookCategoryIdAndLibraryIdAndSave(Integer bookCategoryId, Integer libraryId, boolean b);
	
}
