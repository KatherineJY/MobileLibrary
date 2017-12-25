package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.BookCategory;

public interface BookCategoryCrudRepository extends CrudRepository<BookCategory, Integer>{

	@Query(value="select * where name like CONCAT('%',:name,'%')",nativeQuery=true)
	public BookCategory[] fuzzySearchByName(@Param("name")String name);
	
	@Query(value="select * where author like CONCAT('%',:author,'%')",nativeQuery=true)
	public BookCategory[] fuzzySearchByAuthor(@Param("author")String author);

	public Object findByNameAndAuthorAndPublisher(String name, String author, String publisher);
	

}
