package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.BookCategory;

public interface BookCategoryCrudRepository extends CrudRepository<BookCategory, Integer>{

	@Query("select BookCategory where name like CONCAT('%',:name,'%')")
	public BookCategory[] fuzzySearchByName(@Param("name")String name);
	
	@Query("select BookCategory where author like CONCAT('%',:author,'%')")
	public BookCategory[] fuzzySearchByAuthor(@Param("author")String author);

	public Object findByNameAndAuthorAndPublisher(String name, String author, String publisher);
	

}
