package com.fin.moblibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KatherineJY
 * 页面视图处理
 */

@Controller
@RequestMapping("/")
public class ViewController {
	
	@RequestMapping("/")
	public String viewHome() {
		return "index";
	}

	@RequestMapping("/index")
	public String viewHome2() {
		return "index";
	}
}
