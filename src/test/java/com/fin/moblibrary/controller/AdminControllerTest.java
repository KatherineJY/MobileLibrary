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


public class AdminControllerTest {

	private ApplicationContext ctx = null;
	private AdminController adminController;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		adminController = ctx.getBean(AdminController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testadmincontroller(){
		//login
		System.out.println("login and addbookcategory:");
		BookCategory bookcategory = new BookCategory();
		char categoryID = 'J';
		bookcategory.setCategoryId(categoryID);
		bookcategory.setAuthor("哈弗特");
		bookcategory.setName("亲爱的卧底经济学家");
		bookcategory.setPublisher("中信出版社");
		//bookcategory.setId(1);
		System.out.println("adminlogin:"+adminController.login(bookcategory).isSuccessful());
		
		
}
	public void testhandleEmpiredReserve(){
		//handleEmpiredReserve
		System.out.println("handleEmpiredReserve:");
		adminController.handleEmpiredReserve();
	}

}
