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
import com.fin.moblibrary.service.BookService;

/**
 * @author KatherineJY
 * BookController
 * 处理与书籍相关的操作
 */

@Controller
@RequestMapping("/api/book")
public class BookController {
	
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	/**
	 * returnBook
	 * @param accountId
	 * @param bookId
	 * @param libraryId;
	 * */
	@RequestMapping(value = "/returnbook/{accountId}/{bookId}/{library_id}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper returnBook(@PathVariable("accountId") Integer accountId, @PathVariable("bookId") Integer bookId, @PathVariable("library_id") Integer libraryId){
		logger.info(new Date().toString() + ": " + accountId + " try to return to book "+ bookId + " to library " +libraryId);
		ResponseWrapper responseWrapper = bookService.returnBook(accountId, bookId, libraryId);
		logger.info(new Date().toString() + ": " + "return book " + responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * borrowBook
	 * @param bookId
	 * @param accountId
	 * */
	@RequestMapping(value = "/borrowBook/{accountId}/{bookId}",method = RequestMethod.POST)
	public @ResponseBody ResponseWrapper borrowBook(@PathVariable("accountId") Integer accountId, @PathVariable("bookId") Integer bookId){
		logger.info(new Date().toString() + ": " + accountId + " try to borrow book "+ bookId );
		ResponseWrapper responseWrapper = bookService.borrowBook(accountId, bookId);
		logger.info(new Date().toString() + ": " + "borrow book " + responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * showDetailBook
	 * @param bookCategoryId
	 * */
	@RequestMapping(value = "/showDetailBook/{bookCategoryId}")
	public @ResponseBody ResponseWrapper showDetailBook(@PathVariable("bookCategoryId") Integer bookCategoryId){
		logger.info(new Date().toString() + ": try to show detail book "+ bookCategoryId );
		ResponseWrapper responseWrapper = bookService.showDetailBook( bookCategoryId);
		logger.info(new Date().toString() + ": " + "show detail book " + responseWrapper.toString());
		return responseWrapper;
	}
}
