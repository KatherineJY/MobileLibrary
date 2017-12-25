package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fin.moblibrary.domain.BookCategory;
import com.fin.moblibrary.domain.Library;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.BookCategoryCrudRepository;
import com.fin.moblibrary.repository.LibrarayCrudRepository;

/**
 * @author KatherineJY
 * 与搜索相关的所有操作
 */

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private static Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private BookCategoryCrudRepository bookCategoryCrudRepository;
	
	@Autowired
	private LibrarayCrudRepository librarayCrudRepository;
	
	/**
	 * 搜索 字段为全部
	 * @param keyWords 可能是书名、也可能是作者名 、图书馆的位置信息
	 * 模糊匹配
	 * */
	@RequestMapping(value = "/all/{keyWords}")
	public @ResponseBody ResponseWrapper searchBookAll(@PathVariable("keyWords") String keyWords){
		logger.info(new Date().toString() + ": " + " try to search all with keyWords "+ keyWords);
		BookCategory[] bookCategories = bookCategoryCrudRepository.fuzzySearchByAuthor(keyWords);
		BookCategory[] bookCategories2 = bookCategoryCrudRepository.fuzzySearchByName(keyWords);
		Library[] libraries = librarayCrudRepository.fuzzySearchByAreaAndAddress(keyWords);
		Object[] objs = {bookCategories, bookCategories2, libraries};
		ResponseWrapper responseWrapper = new ResponseWrapper(true,"",objs);
		logger.info(new Date().toString() + ": " + "search all with keyWords "+ keyWords +" return "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 搜索图书馆
	 * @param keyWords 
	 * 模糊匹配
	 * */
	@RequestMapping(value = "/libraray/{keyWords}")
	public @ResponseBody ResponseWrapper searchLibraray(@PathVariable("keyWords") String keyWords){
		logger.info(new Date().toString() + ": " + " try to search libraray with keyWords "+ keyWords);
		ResponseWrapper responseWrapper = new ResponseWrapper(true,"",librarayCrudRepository.fuzzySearchByAreaAndAddress(keyWords));
		logger.info(new Date().toString() + ": " + "search library with keyWords "+ keyWords +" return "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 搜索书名
	 * @param keyWords 
	 * 模糊匹配
	 * */
	@RequestMapping(value = "/bookName/{keyWords}")
	public @ResponseBody ResponseWrapper searchBookName(@PathVariable("keyWords") String keyWords){
		logger.info(new Date().toString() + ": " + " try to search bookName with keyWords "+ keyWords);
		ResponseWrapper responseWrapper = new ResponseWrapper(true,"",bookCategoryCrudRepository.fuzzySearchByName(keyWords));
		logger.info(new Date().toString() + ": " + "search bookName with keyWords "+ keyWords +" return "+ responseWrapper.toString());
		return responseWrapper;
	}
	
	/**
	 * 搜索作者
	 * @param keyWords 
	 * 模糊匹配
	 * */
	@RequestMapping(value = "/author/{keyWords}")
	public @ResponseBody ResponseWrapper searchAuthor(@PathVariable("keyWords") String keyWords){
		logger.info(new Date().toString() + ": " + " try to search author with keyWords "+ keyWords);
		ResponseWrapper responseWrapper = new ResponseWrapper(true,"",bookCategoryCrudRepository.fuzzySearchByAuthor(keyWords));
		logger.info(new Date().toString() + ": " + "search author with keyWords "+ keyWords +" return "+ responseWrapper.toString());
		return responseWrapper;
	}
	
}
