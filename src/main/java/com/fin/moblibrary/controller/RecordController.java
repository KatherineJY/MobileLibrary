package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.service.RecordService;

/**
 * @author KatherineJY
 * 处理与各种记录相关的操作
 */

@Controller
@RequestMapping("/record")
public class RecordController {
	
	private static Logger logger = LoggerFactory.getLogger(RecordController.class);
	
	@Autowired
	private RecordService recordService;
	
	/**
	 * 查看流水记录
	 * @param accountId
	 * @param page 采用分页查询，page指的是返回第几页，起始页是第0页
	 * */
	@RequestMapping(value="/showPayRecord/{accountId}",method =RequestMethod.POST)
	public @ResponseBody ResponseWrapper showPayRecorder(@PathVariable("accountId")Integer accountId) {
		logger.info(new Date().toString() + ": " + accountId + "try to show pay record ");
		ResponseWrapper responseWrapper = recordService.showPayRecord(accountId);
		logger.info(new Date().toString() + ": "+ "show pay record "+ responseWrapper.toString());
		return new ResponseWrapper();
	}
	
	/**
	 * 查看记录借还书
	 * @param accoutId
	 *  
	 */
	@RequestMapping(value="/showBookRecord/{accoutId}",method =RequestMethod.POST)
	public @ResponseBody ResponseWrapper showBookRecorder(@PathVariable("accoutId")Integer accoutId,@PathVariable("page") Integer page) {
		logger.info(new Date().toString() + ": " + accoutId + "try to show book record ");
		ResponseWrapper responseWrapper = recordService.showBookRecord(accoutId);
		logger.info(new Date().toString() + ": "+ "show book record " + responseWrapper.toString());
		return new ResponseWrapper();
	}
	
	/**
	 * 查看未归还的图书
	 * @param accountId
	 * */
	@RequestMapping(value="/showBookBorrow/{accountId}",method =RequestMethod.POST)
	public @ResponseBody ResponseWrapper showBookBorrow(@PathVariable("accountId")Integer accountId) {
		logger.info(new Date().toString() + ": " + accountId + "try to show book borrow ");
		ResponseWrapper responseWrapper = recordService.showBookBorrow(accountId);
		logger.info(new Date().toString() + ": "+ "show book borrow "+ responseWrapper.toString());
		return new ResponseWrapper();
	}
	
	/**
	 * 查看热门图书
	 * */
	@RequestMapping(value="/hotBook",method =RequestMethod.POST)
	public @ResponseBody ResponseWrapper showHotBook() {
		logger.info(new Date().toString() + ": " + "try to show hot book ");
		ResponseWrapper responseWrapper = recordService.showHotBook();
		logger.info(new Date().toString() + ": "+ "show hot book "+ responseWrapper.toString());
		return new ResponseWrapper();
	}
}
