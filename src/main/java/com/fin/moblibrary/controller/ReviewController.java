package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.domain.Review;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.service.ReviewService;

/**
 * @author KatherineJY
 * 添加书评 删除书评 查看书评
 */

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	private static Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private  ReviewService reviewService;
	
	/**
	 * 添加书评
	 * @param accountId
	 * */
	@RequestMapping(value="/addReview",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper showResereveBook(@RequestBody Review review)  {
		logger.info(new Date().toString() + ": " + review.getAccountId() + "try to add review ");
		ResponseWrapper responseWrapper = reviewService.addReview(review);
		logger.info(new Date().toString() + ": "+ "add review "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 查看关于某本书的全部书评
	 * @param bookCategoryId
	 * */
	@RequestMapping(value="/showReviews/{bookCategoryId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper showReviews(@PathVariable("bookCategoryId") Integer bookCategoryId)  {
		logger.info(new Date().toString() + ": " + "try to show review ");
		ResponseWrapper responseWrapper = reviewService.showReviews(bookCategoryId);
		logger.info(new Date().toString() + ": "+ "show reviews"+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 查看某人是否评论过某本书
	 * @param accountId
	 * @param bookCategoryId
	 * */
	@RequestMapping(value="/isReviewedBefore/{accountId}/{bookCategoryId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper isReviewedBefore(@PathVariable("accoutId") Integer accountId,@PathVariable("bookCategoryId") Integer bookCategoryId)  {
		logger.info(new Date().toString() + ": " + "is "+ accountId +" has reviewd "+bookCategoryId + " before.");
		ResponseWrapper responseWrapper = reviewService.isReviewedBefore(accountId,bookCategoryId);
		logger.info(new Date().toString() + ": "+ "is reviewed?"+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 修改某人对某书的评论
	 * @param accountId
	 * @param bookCategoryId
	 * */
	@RequestMapping(value="/changeReview",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper changeReview(@RequestBody Review review)  {
		logger.info(new Date().toString() + ": try to change review about " + review.toString());
		ResponseWrapper responseWrapper = reviewService.changeReview(review);
		logger.info(new Date().toString() + ": "+ "change review"+ responseWrapper.toString());
		return responseWrapper;
	}
}
