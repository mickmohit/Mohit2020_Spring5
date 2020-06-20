package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController{

	private static final String Path="/error";
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return Path;
	}
	
	@RequestMapping("/error")
	public String error()
	{
		return "No Mapping found";
	}

}
