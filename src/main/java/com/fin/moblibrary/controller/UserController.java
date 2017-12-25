package com.fin.moblibrary.controller;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.service.UserService;

/**
 * @author KatherineJY
 * UserController
 * 负责处理前端传来的与用户相关的操作
 */

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param phone
	 * @param pwd
	 * */
	@RequestMapping(value = "/login/{phone}/{pwd}", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper login(@PathVariable("phone") String phone, @PathVariable("pwd") String pwd) throws IOException {
		logger.info(new Date().toString() + ": " + phone + " try to login ");
		ResponseWrapper responseWrapper = userService.login(phone, pwd);
		logger.info(new Date().toString() + ": " + "login " + responseWrapper.toString());
		return responseWrapper;
	}

	/**
	 * 注册
	 * @param account
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper register(@RequestBody Account account) {
		logger.info(new Date().toString() + ": " + account.getPhone() + "try to register");
		ResponseWrapper responseWrapper = userService.register(account);
		logger.info(new Date().toString() + ": "+ "register " + responseWrapper.toString());
		return new ResponseWrapper();
	}
	
	/**
	 * 缴纳/退还押金
	 * @param accoutId
	 * @param status 为0表示退还押金；为1表示缴纳押金
	 * */
	@RequestMapping(value = "/deposit/{accoutId}/{status}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper deposit(@PathVariable("accoutId") Integer accoutId,@PathVariable("status") boolean is_deposit) {
		logger.info(new Date().toString() + ": " + accoutId + "try to deposit "+ is_deposit);
		ResponseWrapper responseWrapper = userService.deposit(accoutId,is_deposit);
		logger.info(new Date().toString() + ": "+ "deposit "+ is_deposit + " " + responseWrapper.toString());
		return new ResponseWrapper();
	}

	/**
	 * 充值
	 * @param accoutId
	 * @param amount
	 * */
	@RequestMapping(value = "/recharge/{accoutId}/{amount}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper recharge(@PathVariable("accoutId") Integer accoutId, @PathVariable("amount") double amount) {
		logger.info(new Date().toString() + ": " + accoutId + "try to recharge "+ amount);
		ResponseWrapper responseWrapper = userService.recharge(accoutId,amount);
		logger.info(new Date().toString() + ": "+ "recharge "+ amount + " " + responseWrapper.toString());
		return new ResponseWrapper();
	}
	
	/**
	 * 重置密码
	 * @param accoutId
	 * @param newPwd
	 * */
	@RequestMapping(value = "/ResetPwd/{accoutId}/{newPwd}", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper ResetPwd(@PathVariable("phone") Integer accoutId, @PathVariable("newPwd") String newPwd) {
		logger.info(new Date().toString() + ": " + accoutId + "try to change pwd");
		ResponseWrapper responseWrapper = userService.resetPwd(accoutId,newPwd);
		logger.info(new Date().toString() + ": "+ "change pwd " + responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 重置手机号
	 * @param accoutId
	 * @param newPhone
	 * */
	@RequestMapping(value = "/ResetPhone/{accoutId}/{newPhone}", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper ResetPhone(@PathVariable("accoutId") Integer accoutId, @PathVariable("newPhone") String newPhone) {
		logger.info(new Date().toString() + ": " + accoutId + "try to change phone");
		ResponseWrapper responseWrapper = userService.resetPhone(accoutId,newPhone);
		logger.info(new Date().toString() + ": "+ "change phone " + responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 发送验证码
	 * @param phone
	 * @throws ClientException 
	 * @throws ServerException 
	 * */
	@RequestMapping(value = "/getRandomCode/{phone}", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper getRandomCode(@PathVariable("phone") String phone) throws ServerException, ClientException{
		logger.info(new Date().toString() + ": " + phone + "try to get a random code");
		ResponseWrapper responseWrapper = userService.getRandomCode(phone);
		logger.info(new Date().toString() + ": " + "have seen a msg with random code: "+responseWrapper.getBody());
		return responseWrapper;
	}
	
}
