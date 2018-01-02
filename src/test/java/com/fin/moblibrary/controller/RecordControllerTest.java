package com.fin.moblibrary.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RecordControllerTest {
	private ApplicationContext ctx = null;
	private RecordController recordController;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		recordController = ctx.getBean(RecordController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testrecordcontroller(){
		//showPayRecorder
		recordController.showBookBorrow(8);
	}
	public void testshowBookRecorder(){
		recordController.showBookRecorder(8, 1);
	}
	public void testshowPayRecorder(){
		recordController.showPayRecorder(8);
	}
	public void testshowHotBook(){
		recordController.showHotBook();
	}
}
