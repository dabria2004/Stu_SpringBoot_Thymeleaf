package com.ppt.studentmanagement.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ppt.studentmanagement.dto.*;
import com.ppt.studentmanagement.model.StudentBean;
import com.ppt.studentmanagement.dao.*;

@Controller
public class StudentController {

	@Autowired
	private StudentDAO studentDao;
	
	@Autowired
	private ClassDAO classDao;
	
	@GetMapping(value = "/setupaddstudent")
	public ModelAndView setupaddstudent(ModelMap model) {
		List<ClassResponseDTO> courseList = classDao.selectAll();
		model.addAttribute("courseList", courseList);
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@GetMapping(value = "/setupaddstudentagain")
	public ModelAndView setupaddstudentagain(ModelMap model) {
		List<ClassResponseDTO> courseList = classDao.selectAll();
		model.addAttribute("courseList", courseList);
		model.addAttribute("success", "Successfully Registered!!");
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@RequestMapping(value = "/addstudent", method=RequestMethod.POST)
	public String addstudent(@ModelAttribute("sbean")StudentBean sbean,ModelMap model) {
		
		List<ClassResponseDTO> courseList = classDao.selectAll();
		model.addAttribute("courseList", courseList);
		if (sbean.getAttendCourses().size() == 0) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU001";
		}
		if (sbean.getStudentname().isBlank() || sbean.getDob().isBlank() || sbean.getGender().isBlank()
				|| sbean.getPhone().isBlank() || sbean.getEducation().isBlank()) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU001";
		}
		List<StudentResponseDTO> studentList = studentDao.selectAll();
		if (studentList == null) {
			studentList = new ArrayList<>();
		}
		if (studentList.size() == 0) {
			sbean.setStudentid("STU001");
		} else {
			int tempId = Integer.parseInt(studentList.get(studentList.size() - 1).getStudentid().substring(3)) + 1;
			String userId = String.format("STU%03d", tempId);
			sbean.setStudentid(userId);
		}
		StudentRequestDTO dto = new StudentRequestDTO();
		dto.setStudentid(sbean.getStudentid());
		dto.setStudentname(sbean.getStudentname());
		dto.setDob(sbean.getDob());
		dto.setGender(sbean.getGender());
		dto.setPhone(sbean.getPhone());
		dto.setEducation(sbean.getEducation());
		studentDao.insertData(dto);
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			studentDao.insertStudentCourse(sbean.getStudentid(), attendCourses[i]);
		}
		return "redirect:/setupaddstudentagain";	
	}
	
	@GetMapping("/setupstudentsearch")
	public String studentManagement(ModelMap model) {	
		
		List<StudentResponseDTO> studentList = studentDao.selectAll();
		List<ClassResponseDTO> cdto = new ArrayList<>();
		for (StudentResponseDTO student : studentList) {
			List<String> clist = classDao.selectCidByStuid(student.getStudentid());
			student.setAttendCourses(clist);
		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("courseList", cdto);
		return "STU003";
	}
	
	@GetMapping("/studentdetail")
	public ModelAndView seeMore(@RequestParam("id") String studentid, ModelMap model) {
		
		StudentResponseDTO dto = studentDao.selectStudentById(studentid);
		List<ClassResponseDTO> stuCourseResList = classDao.selectCoursesByStudentId(studentid);
		List<ClassResponseDTO> allCourses = classDao.selectAll();

		ArrayList<String> stuCourseList=new ArrayList<String>();
		for(ClassResponseDTO course: stuCourseResList) {
			stuCourseList.add(course.getClassid());
		}
		StudentBean sbean=new StudentBean();
		sbean.setStudentid(studentid);
		sbean.setStudentname(dto.getStudentname());
		sbean.setDob(dto.getDob());
		sbean.setGender(dto.getGender());
		sbean.setPhone(dto.getPhone());
		sbean.setEducation(dto.getEducation());
		sbean.setAttendCourses(stuCourseList);
		model.addAttribute("data", sbean);
		model.addAttribute("courseList", allCourses);
		return new ModelAndView ("STU002", "sbean", sbean);
	}
	
	@PostMapping("/updatestudent")
	public String updateStudent(@ModelAttribute("sbean") StudentBean sbean, ModelMap model) {
		System.out.println("sbean => " + sbean);
		List<ClassResponseDTO> courseList = classDao.selectAll();
		model.addAttribute("courseList", courseList);
		if (sbean.getAttendCourses().size() == 0) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU002";
		}
		if (sbean.getStudentname().isBlank() || sbean.getDob().isBlank() || sbean.getGender().isBlank()
				|| sbean.getPhone().isBlank() || sbean.getEducation().isBlank()) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU002";
		}
		
		StudentRequestDTO dto = new StudentRequestDTO();
		dto.setStudentid(sbean.getStudentid());
		dto.setStudentname(sbean.getStudentname());
		dto.setDob(sbean.getDob());
		dto.setGender(sbean.getGender());
		dto.setPhone(sbean.getPhone());
		dto.setEducation(sbean.getEducation());
		
		studentDao.updateData(dto);
		studentDao.deleteAttendCoursesByStudentId(sbean.getStudentid());
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			studentDao.insertStudentCourse(sbean.getStudentid(), attendCourses[i]);
		}
//		model.addAttribute("success", "Successfully updated!!");
		return "redirect:/setupstudentsearch";
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("id") String id) {
		studentDao.deleteAttendCoursesByStudentId(id);
		studentDao.deleteStudentById(id);
		return "redirect:/setupstudentsearch";
	}
	
	@PostMapping("/searchstudent")
	public String searchStudent(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("course") String course, ModelMap model) {
		String sid = id.isBlank() ? ")#<>(}" : id;
		String sname = name.isBlank() ? ")#<>(}" : name;
		String scourse = course.isBlank() ? ")#<>(}" : course;
		System.out.println( "sid => " + sid + " " + "sname => " + sname + " " + "scourse => " + scourse);
		List<StudentResponseDTO> studentList = studentDao.selectStudentListByIdOrNameOrCourse(sid, sname, scourse);
		for (StudentResponseDTO student : studentList) {
			List<String> clist = classDao.selectCidByStuid(student.getStudentid());
			student.setAttendCourses(clist);
		}
		if (studentList.size() == 0) {
			studentList = studentDao.selectAll();
			for (StudentResponseDTO student : studentList) {
				List<String> clist = classDao.selectCidByStuid(student.getStudentid());
				student.setAttendCourses(clist);
			}
			model.addAttribute("studentList", studentList);
			return "STU003";
		}
		model.addAttribute("studentList", studentList);
		return "STU003";	
	}
}
