package com.ppt.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@GetMapping(value="/menu")
	public String menu(ModelMap model) {
	return "MNU001";
	}
	
	@GetMapping(value="/logout")
	public String logout(ModelMap model) {
	return "LGN001";
	}
	
}
