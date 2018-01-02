package com.fin.moblibrary.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javassist.compiler.ast.Keyword;

public class SearchControllerTest {

	private ApplicationContext ctx = null;
	private SearchController searchController;
	
	
	@Before
	public void setup(){
	
		ctx = new FileSystemXmlApplicationContext("F:\\tools\\sts-bundle\\workspace-sts-3.8.0.RELEASE\\MobileLibrary\\src\\main\\webapp\\WEB-INF\\spring\\application-config.xml");
		searchController = ctx.getBean(SearchController.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		System.out.println("tearDown");
		ctx = null;
	}
	
	@Test
	public void testreserveController(){
		
		searchController.searchBookAll("ÄªÑÔ");
	}
	public void testsearchAuthor(){
		searchController.searchAuthor("ÄªÑÔ");
	}
	
	public void testsearchBookName(){
		searchController.searchBookName("ÍÜ");
	}
	
	public void testsearchLibrary(){
		searchController.searchLibraray("Îäºî");
	}
}
