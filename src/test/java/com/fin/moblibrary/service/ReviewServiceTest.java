package com.fin.moblibrary.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fin.moblibrary.domain.Review;

public class ReviewServiceTest {

	private ApplicationContext ctx = null;
	private ReviewService reviewservice;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		reviewservice = ctx.getBean(ReviewService.class);
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
		review.setAccountId(1);
		review.setBookCategoryId(3);
		review.setContent("现实魔幻主义书籍");
		reviewservice.addReview(review);
	}
}
