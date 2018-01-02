package com.fin.moblibrary.service;
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

import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.service.UserService;


public class BookServiceTest {

	private ApplicationContext ctx = null;
	private BookService bookservice;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		bookservice = ctx.getBean(BookService.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testBookService(){

		bookservice.borrowBook(1, 1);
		
		bookservice.returnBook(1, 1, 1);
		
		bookservice.showDetailBook(1).toString();
		
	}
}
