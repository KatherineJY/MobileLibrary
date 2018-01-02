package com.fin.moblibrary.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ReserveServiceTest {

	private ApplicationContext ctx = null;
	private ReserveService reserveservice;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		reserveservice = ctx.getBean(ReserveService.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testBookService(){
		reserveservice.reserveBook(1, 3, 1);
		
		reserveservice.cancelReserve(1, 3);
		
		reserveservice.showResereveBook(1);
		
	}
}
