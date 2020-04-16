package com.example.cateringProject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController{
	
	private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);
	
	@RequestMapping("/error")
	public String handleError() {
		log.info("Error happened in program");
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
