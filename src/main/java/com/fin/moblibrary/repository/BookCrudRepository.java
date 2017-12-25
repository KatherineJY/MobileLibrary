package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Book;

public interface BookCrudRepository extends CrudRepository<Book, Integer>{
	
	@Modifying
	@Query("update Book set libraryId = :libraryId where bookId =:bookId")
	public void updatePosition(@Param("libraryId")Integer libraryId,@Param("bookId")Integer bookId);
	
	@Modifying
	@Query("update Book set borrow = :borrow where bookId =:bookId")
	public void updateIsBorrow(@Param("borrow")boolean borrow,@Param("bookId")Integer bookId);
	
	@Modifying
	@Query("update Book set save = :save where bookId =:bookId")
	public void updateIsSave(@Param("save")boolean save,@Param("bookId")Integer bookId);

	public Book[] findAllByBookCategoryIdAndBorrow(Integer bookCategoryId, boolean borrow);

	public Book findAllByBookCategoryIdAndLibraryIdAndSave(Integer bookCategoryId, Integer libraryId, boolean b);
	
}
