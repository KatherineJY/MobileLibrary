package com.fin.moblibrary.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration

public class AdminServiceTest {
	
	private ApplicationContext ctx = null;
	
	@Before
	public void setup(){
	
		ctx = new ClassPathXmlApplicationContext("application-config.xml");
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testAdminService(){
		
		
	}
}
