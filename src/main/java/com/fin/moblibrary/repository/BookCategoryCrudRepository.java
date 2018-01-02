package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.BookCategory;

public interface BookCategoryCrudRepository extends CrudRepository<BookCategory, Integer>{

//	@Query(value="select * from book_category where name like CONCAT('%',:name,'%')",nativeQuery=true)
//	public BookCategory[] fuzzySearchByName(@Param("name")String name);
	public BookCategory[] findByNameLike(String name);
//	@Query(value="SELECT * FROM com.fin.moblibrary.domain.BookCategory bookCategory WHERE bookCategory.name like :name",nativeQuery=true)
	//public BookCategory[] fuzzySearchByName(@Param("name")String name);
	
	//@Query(value="SELECT * FROM book_category WHERE author like ?1;)",nativeQuery=true)
	//public BookCategory[] fuzzySearchByAuthor(String author);
	public BookCategory[] findByAuthorLike(String author);
	
//	@Query(value="select * where publisher like CONCAT('%',:publisher,'%')",nativeQuery=true)
//	public BookCategory[] fuzzySearchByPublisher(@Param("publisher")String publisher);

	public BookCategory findByNameAndAuthorAndPublisher(String name, String author, String publisher);
	

}
