package com.fin.moblibrary.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RecordServiceTest {

	private ApplicationContext ctx = null;
	private RecordService recordservice;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		recordservice = ctx.getBean(RecordService.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testRecordService(){
		recordservice.showBookBorrow(1);
		
		recordservice.showBookRecord(1);
		
		recordservice.showHotBook();
		
		recordservice.showPayRecord(1);
		
	}
}
