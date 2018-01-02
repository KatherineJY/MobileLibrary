package com.fin.moblibrary.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fin.moblibrary.domain.Review;
import com.fin.moblibrary.service.BookService;

public class ReviewControllerTest {

	private ApplicationContext ctx = null;
	private ReviewController reviewController;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		reviewController = ctx.getBean(ReviewController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testBookService(){
		Review review = new Review();
		review.setAccountId(8);
		review.setBookCategoryId(4);
		review.setContent("诺贝尔文学奖获奖书");
		reviewController.showResereveBook(review);
		
	}
	public void testshowReviews(){
		Review review = new Review();
		review.setAccountId(8);
		review.setBookCategoryId(4);
		review.setContent("一本好书");
		//showResereveBook(review);
	}
	
	
}
