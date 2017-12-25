package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.service.AdminService;

/**
 * @author KatherineJY
 * 系统管理员才允许执行的操作
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * addBookCategory
	 * @param BookCategory bookCategory
	 * */
	@RequestMapping(value = "/addBookCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper login(@RequestBody BookCategory bookCategory ) {
		logger.info(new Date().toString() + ": admin try to addBookCategory" + bookCategory.toString());
		ResponseWrapper responseWrapper = adminService.addBookCategory(bookCategory);
		logger.info(new Date().toString() + ": " + "admin addBookCategory " + responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * handleEmpliredReserve
	 * */
	@Scheduled(cron="0 0 0 * * ?")
	public void handleEmpiredReserve() {
		logger.info(new Date().toString() + ": admin try to handleEmpiredReserve");
		ResponseWrapper responseWrapper = adminService.handleEmpiredReserve();
		logger.info(new Date().toString() + ": " + "adin handleEmpiredReserve " + responseWrapper.toString());
	}
}
