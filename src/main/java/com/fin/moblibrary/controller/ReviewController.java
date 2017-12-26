package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		logger.info(new Date().toString() + ": " + review.getAccountId() + "try to show reserve book ");
		ResponseWrapper responseWrapper = reviewService.addReview(review);
		logger.info(new Date().toString() + ": "+ "show reserve book "+ responseWrapper.toString());
		return responseWrapper;
	}
}
