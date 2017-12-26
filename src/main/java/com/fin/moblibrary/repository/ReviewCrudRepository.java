package com.fin.moblibrary.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Review;
import com.fin.moblibrary.model.ReviewMultiKeysClass;

public interface ReviewCrudRepository extends CrudRepository<Review, ReviewMultiKeysClass>{

	Review[] findByBookCategoryId(Integer bookCategoryId);

	@Modifying
	@Query("update Review set content = :content where accountId=:accountId and bookCategoryId=:bookCategoryId")
	void updateContentByAccountIdAndBookCategoryId(@Param("content") String content, @Param("accountId") Integer accountId,@Param("bookCategoryId") Integer bookCategoryId);

}
