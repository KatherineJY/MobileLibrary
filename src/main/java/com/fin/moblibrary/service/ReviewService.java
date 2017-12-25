package com.fin.moblibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.moblibrary.domain.Review;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.ReviewCrudRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewCrudRepository reviewCrudRepository;
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;

	/**
	 * 添加书评
	 * */
	public ResponseWrapper addReview(Review review) {
		if( review.getAccountId()==null || accountCrudRepository.findOne(review.getAccountId()) == null )
			return new ResponseWrapper(false,"account isn't exist",null);
		if( review.getBookCategoryId()==null || bookCategoryCrudRepository.findOne( review.getBookCategoryId() )==null )
			return new ResponseWrapper(false,"wrong bookCategoryId",null);
		if( review.getContent()==null || review.getContent().length()==0 )
			return new ResponseWrapper(false,"empty content",null);
		reviewCrudRepository.save(review);
		return new ResponseWrapper(true,"",null);
	}
	
	
}
