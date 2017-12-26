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
import com.fin.moblibrary.service.ReserveService;

/**
 * @author KatherineJY
 * 和预定操作有关
 */

@Controller
@RequestMapping("/reserve")
public class ReserveController {
	
	private static Logger logger = LoggerFactory.getLogger(ReserveController.class);
	
	@Autowired
	private ReserveService reserveService;
	
	/**
	 * 查看预定图书
	 * @param accountId
	 * */
	@RequestMapping(value="/showReserveBook/{accountId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper showResereveBook(@PathVariable("accountId")Integer accountId)  {
		logger.info(new Date().toString() + ": " + accountId + "try to show reserve book ");
		ResponseWrapper responseWrapper = reserveService.showResereveBook(accountId);
		logger.info(new Date().toString() + ": "+ "show reserve book "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 预定图书
	 * @param accountId
	 * @param bookCategoryId
	 * @param libraryId
	 * */
	@RequestMapping(value="/reserveBook/{accountId}/{bookCategoryId}/{libraryId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper reserveBook(@PathVariable("accountId")Integer accountId,@PathVariable("bookCategoryId")Integer bookCategoryId,@PathVariable("libraryId")Integer libraryId)  {
		logger.info(new Date().toString() + ": " + accountId + "try to show reserve book "+bookCategoryId);
		ResponseWrapper responseWrapper = reserveService.reserveBook(accountId,bookCategoryId,libraryId);
		logger.info(new Date().toString() + ": "+ "reserve book "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 取消图书预定
	 * @param accountId
	 * @param bookCategoryId
	 * */
	@RequestMapping(value="/reserveBook/{accountId}/{bookCategoryId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper cancelReserve(@PathVariable("accountId")Integer accountId,@PathVariable("bookCategoryId")Integer bookCategoryId)  {
		logger.info(new Date().toString() + ": " + accountId + "try to cancel reserve book "+bookCategoryId);
		ResponseWrapper responseWrapper = reserveService.cancelReserve(accountId,bookCategoryId);
		logger.info(new Date().toString() + ": "+ "cancel reserve book "+ responseWrapper.toString());
		return responseWrapper;
	}
}
