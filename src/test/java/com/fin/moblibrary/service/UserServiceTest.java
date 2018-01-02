package com.fin.moblibrary.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.repository.AccountCrudRepository;


public class UserServiceTest {

	private ApplicationContext ctx = null;
	private UserService userservice ;
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		userservice = ctx.getBean(UserService.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testUserService(){
//		//rigister
//		Account account1 = new Account();
//		account1.setId(0);
//		account1.setBalance(0);
//		account1.setDeposit(true);
//		account1.setUsername("张三");
//		account1.setPassword("123456");
//		account1.setPhone("17761231234");
//		System.out.println("register:"+userservice.register(account1).isSuccessful());
		//login
		try {
			System.out.println("login"+userservice.login("17761231234", "123456").isSuccessful());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deposit
		System.out.println("deposit:");
		//userservice.deposit(1, true).isSuccessful();//缴纳
//		System.out.println("deposit:"+userservice.deposit(1, false).isSuccessful());//退还
		//System.out.println("deposit:"+userservice.deposit(10, true).isSuccessful());//用户不存在
		
		//recharge
		System.out.println("recharge:");
		userservice.recharge(1, 20).isSuccessful();//充值
		//System.out.println("recharge:"+userservice.recharge(10, 10).isSuccessful());//用户不存在
		
		//resetPwd
		System.out.println("resetPwd:");
		userservice.resetPwd(1, "123").isSuccessful();//修改密码
		//System.out.println("resetPwd:"+userservice.resetPwd(10, "123").isSuccessful());//用户不存在
		
		//getRandomCode
		try {
			System.out.println("getrandcode:"+userservice.getRandomCode("17761231234").isSuccessful());
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//encoderPwd
		//System.out.println("encodePwd:"+userservice.encoderPwd("123456"));
		//System.out.println("encodePwd:"+userservice.decoderPwd("123456"));

	


	

		
		
	}
}
