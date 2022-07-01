package com.ppt.studentmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppt.studentmanagement.dao.UserDAO;
import com.ppt.studentmanagement.dto.UserResponseDTO;

@Controller
public class LoginController {
	
	@Autowired
	private UserDAO userDao;
	
	@GetMapping(value="/menu")
	public String menu(ModelMap model) {
	return "MNU001";
	}
	
	@GetMapping(value="/login")
	public String login(ModelMap model) {
	return "LGN001";
	}
	
	@GetMapping(value="/logout")
	public String logout(ModelMap model) {
	return "LGN001";
	}
	
	@RequestMapping(value = "/welcomepage", method = RequestMethod.POST)
	public String finalexampage(@RequestParam("email") String email,@RequestParam("password") String password,
	HttpSession session,ModelMap model) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);
		if(userDao.checkLogin(email, password)) {
			UserResponseDTO dto=userDao.selectUserByEmail(email);
			session.setAttribute("userInfo", dto);
			session.setAttribute("date", currentDate);
			return "MNU001";
		}else {
			model.addAttribute("error","Email and Passwords do not match!!");
			return "LGN001";
		}
	}
}
