package com.ppt.studentmanagement.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ppt.studentmanagement.dao.*;
import com.ppt.studentmanagement.dto.UserResponseDTO;

public class TestHelper {
	@Autowired
	UserDAO dao;
	public void test() {
		List<UserResponseDTO> res = dao.selectAll();
		System.out.println("res is => " + res.size());
	}
}
