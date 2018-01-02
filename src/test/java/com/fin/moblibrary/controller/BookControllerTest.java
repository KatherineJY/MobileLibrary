package com.fin.moblibrary.controller;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fin.moblibrary.domain.BookCategory;
public class BookControllerTest {

	private ApplicationContext ctx = null;
	private BookController bookController;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		bookController = ctx.getBean(BookController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testborrowbookcontroller(){
		//borrowbook
//		System.out.println("borrow book:");
		bookController.borrowBook(6, 10);
		
}
	public void testreturnBook(){
		//returnBook
		System.out.println("return book:");
		bookController.returnBook(6, 9, 5);
	}
	
	public void testshowDetailBook(){
		//showDetailBook
		System.out.println("show detail:");
		bookController.showDetailBook(4);
	}
}
