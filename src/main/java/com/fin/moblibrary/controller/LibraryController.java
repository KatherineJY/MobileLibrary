package com.fin.moblibrary.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.service.LibraryService;

@Controller
@RequestMapping("/api/library")
public class LibraryController {
	
	private static Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@Autowired
	private LibraryService libraryService;
	
	/**
	 * showAllLibraries
	 * */
	@RequestMapping(value = "/showAllLibraries")
	public @ResponseBody ResponseWrapper login() {
		logger.info(new Date().toString() + ": show all libraries");
		ResponseWrapper responseWrapper = libraryService.showAllLibraries();
		logger.info(new Date().toString() + ": " + "show all libraries" + responseWrapper.toString());
		return responseWrapper;
	}
}
