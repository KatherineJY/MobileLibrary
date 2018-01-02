package com.fin.moblibrary.controller;

import javax.print.DocFlavor.READER;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fin.moblibrary.service.BookService;

public class ReserveControllerTest {

	private ApplicationContext ctx = null;
	private ReserveController reserveController;
	
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		reserveController = ctx.getBean(ReserveController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testreserveController(){
		reserveController.reserveBook(1, 3, 1);
	}
	
	public void testshowReserveBook(){
		reserveController.showResereveBook(1);
	}
	
	public void testcancelReserve(){
		reserveController.cancelReserve(1, 3);
	}
}
