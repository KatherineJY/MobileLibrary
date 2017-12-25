package com.fin.moblibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.fin.moblibrary.domain.Review;
import com.fin.moblibrary.model.ReviewMultiKeysClass;

public interface ReviewCrudRepository extends CrudRepository<Review, ReviewMultiKeysClass>{

}
