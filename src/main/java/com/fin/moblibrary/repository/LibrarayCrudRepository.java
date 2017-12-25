package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Library;

public interface LibrarayCrudRepository extends CrudRepository<Library, Integer>{
	
	@Query("select Library where area like CONCAT('%',:key,'%') or address like CONCAT('%',:key,'%')")
	public Library[] fuzzySearchByAreaAndAddress(@Param("key")String key);
}
