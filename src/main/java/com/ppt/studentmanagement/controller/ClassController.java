package com.ppt.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.ppt.studentmanagement.dao.ClassDAO;
import com.ppt.studentmanagement.dto.*;
import com.ppt.studentmanagement.model.*;

@Controller
public class ClassController {
	
	@Autowired
	ClassDAO cdao;
	
	@GetMapping(value = "/setupaddclass")
	public ModelAndView setupaddclass(ModelMap model, ClassBean cbean) {
		List<ClassResponseDTO> classlist = cdao.selectAll();
		if (classlist.size() == 0) {
			cbean.setClassid("COU001");
		}else {
		int tempId = Integer.parseInt(classlist.get(classlist.size() - 1).getClassid().substring(3)) + 1;
		String classid = String.format("COU%03d", tempId);
		cbean.setClassid(classid);
		}
		return new ModelAndView("BUD003", "cbean", cbean);
	}
	
	@GetMapping(value = "/setupaddclassagain")
	public ModelAndView setupaddclassagain(ModelMap model) {
		ClassBean cbean = new ClassBean();
		List<ClassResponseDTO> classlist = cdao.selectAll();
		if (classlist.size() == 0) {
			cbean.setClassid("COU001");
		}else {
		int tempId = Integer.parseInt(classlist.get(classlist.size() - 1).getClassid().substring(3)) + 1;
		String classid = String.format("COU%03d", tempId);
		cbean.setClassid(classid);
		}
		model.addAttribute("success", "Successfully Registered!!");
		return new ModelAndView("BUD003", "cbean", cbean);
	}
	
	@RequestMapping(value = "/addclass", method = RequestMethod.POST)
	public String addclass(@ModelAttribute("cbean") @Validated ClassBean cbean, BindingResult bs, ModelMap model) {
		if(bs.hasErrors()) {
			return "BUD003";
		}
		if (cbean.getClassid().equals("") || cbean.getClassname().equals("")) {
			model.addAttribute("error", "You must fullfill the fields!!");
			return "BUD003";
		} else {
			ClassRequestDTO dto = new ClassRequestDTO();
			dto.setClassid(cbean.getClassid());
			dto.setClassname(cbean.getClassname());
			System.out.println("ClassController => " + dto.getClassid() + " " + dto.getClassname());
			int rs = cdao.insertData(dto);
			if (rs > 0) {
				return "redirect:/setupaddclass";
			} 
			else {
				model.addAttribute("fail", "Insert Failed!!");
				return "BUD003";
			}
		}
	}

}
