package com.fin.moblibrary.controller;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.fin.moblibrary.domain.Account;

public class UserControllerTest {
	private ApplicationContext ctx = null;
	private UserController userController;
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		userController = ctx.getBean(UserController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testUserlogin(){
		//login
		try {
			System.out.println(userController.login("17761232345", "123456").isSuccessful());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//register
//		Account account1 = new Account();
//		account1.setId(4);
//		account1.setBalance(10);
//		account1.setDeposit(true);
//		account1.setUsername("ฝ๐วน");
//		account1.setPassword("123456");
//		account1.setPhone("17761258856");
//		userController.register(account1);
//		
		//deposit
//		userController.deposit(8,true);
		
		//recharge
		userController.recharge(6, 14);
		
		//resertPwd
//		userController.ResetPwd(5, "123456");
		
		//resertPhone
//		userController.ResetPhone(5, "17761231237");
		
		//getrandmocode
//		try {
//			userController.getRandomCode("17761234567");
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
		public void testregister(){
			//register
			Account account1 = new Account();
			account1.setId(4);
			account1.setBalance(10);
			account1.setDeposit(true);
			account1.setUsername("ฝ๐วน");
			account1.setPassword("123456");
			account1.setPhone("17761258856");
			userController.register(account1);
			
		}
		

}
