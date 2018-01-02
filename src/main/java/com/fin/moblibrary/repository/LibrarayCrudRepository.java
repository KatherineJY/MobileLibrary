package com.fin.moblibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Library;

public interface LibrarayCrudRepository extends CrudRepository<Library, Integer>{

//	@Query(value="select * from Library where area like CONCAT('%',:key,'%') or address like CONCAT('%',:key,'%')",nativeQuery=true)
//	public Library[] fuzzySearchByAreaAndAddress(@Param("key")String key);
	public Library[] findByAreaLike(String area);
	public Library[] findByAddressLike(String address);
	
	@Query( value="select * from library",nativeQuery=true )
	public List<Library> findAllLibrary();
	

}
