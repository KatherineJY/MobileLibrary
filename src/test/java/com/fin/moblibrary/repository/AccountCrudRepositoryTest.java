package com.fin.moblibrary.repository;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.service.UserService;

@WebAppConfiguration
@ContextConfiguration

public class AccountCrudRepositoryTest {
	

	private ApplicationContext ctx = null;
	private AccountCrudRepository accountCrudRepository;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testfindByPhone(){
		this.accountCrudRepository.findByPhone("123445566778");
		System.out.println();
		
		
	}
}
