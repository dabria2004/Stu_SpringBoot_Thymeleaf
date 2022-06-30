package com.ppt.studentmanagement.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ppt.studentmanagement.dto.StudentRequestDTO;
import com.ppt.studentmanagement.dto.StudentResponseDTO;

@Repository
public class StudentDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insertData(StudentRequestDTO dto) {
		int result = 0;
		String sql = "insert into student values(?, ?, ?, ?, ?, ?)";
		result = jdbcTemplate.update(sql, dto.getStudentid(), dto.getStudentname(), dto.getDob(), dto.getGender(), dto.getPhone(), dto.getEducation());
		return result;
	}
	
	public List<StudentResponseDTO> selectAll() {
		String sql = "select * from student";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new StudentResponseDTO(
				rs.getString("student_id"),
				rs.getString("student_name"),
				rs.getString("dob"),
				rs.getString("gender"),
				rs.getString("phone"),
				rs.getString("education")));
	}
	
	public int deleteStudentById(String id) {
		int result = 0;
		String sql = "delete from student where student_id=?";
		result = jdbcTemplate.update(sql, id);
		return result;
	}
	
	public int insertStudentCourse(String course_id, String student_id) {
		int result = 0;
		String sql = "insert into selected_courses (student_id, class_id) values(?, ?)";
		result = jdbcTemplate.update(sql, course_id, student_id);
		return result;
	}
	
	public StudentResponseDTO selectStudentById(String student_id) {
		String sql = "select * from student where student_id=?";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->new StudentResponseDTO(
		rs.getString("student_id"),
		rs.getString("student_name"),
		rs.getString("dob"),
		rs.getString("gender"),
		rs.getString("phone"),
		rs.getString("education")),
		student_id);			
	}
	
	public int updateData(StudentRequestDTO dto) {
		int result = 0;
		String sql = "update student set student_name=?, dob=?, gender=?, phone=?, education=? where student_id=?";
		result = jdbcTemplate.update(sql, dto.getStudentname(), dto.getDob(), dto.getGender(), dto.getPhone(), dto.getEducation(), dto.getStudentid());
		return result;
	}
	
	public int deleteAttendCoursesByStudentId(String student_id) {
		int result = 0;
		String sql = "delete from selected_courses where student_id=?";
		result = jdbcTemplate.update(sql, student_id);
		return result;
	}
	
	public List<StudentResponseDTO> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {	
		String sql = "select distinct student.student_id, student.student_name "
				+ "from selected_courses join student "
				+ "on selected_courses.student_id = student.student_id join class "
				+ "on selected_courses.class_id = class.class_id "
				+ "where student.student_id like ? or student.student_name like ? or class.class_name like ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new StudentResponseDTO(
				rs.getString("student_id"),
				rs.getString("student_name"),
				rs.getString("dob"),
				rs.getString("gender"),
				rs.getString("phone"),
				rs.getString("education")),
				"%" + id + "%",
				"%" + name + "%",
				"%" + course + "%");
	}
}
