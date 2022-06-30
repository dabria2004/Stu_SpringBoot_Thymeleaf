package com.ppt.studentmanagement.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ppt.studentmanagement.dto.ClassRequestDTO;
import com.ppt.studentmanagement.dto.ClassResponseDTO;

@Repository
public class ClassDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public int insertData(ClassRequestDTO dto) {
		int result=0;
		String sql="insert into class(class_id,class_name) values (?,?)";
		result=jdbcTemplate.update(sql,dto.getClassid(),dto.getClassname());
		return result;
		}
	
	
	public int deleteData(ClassRequestDTO dto) {
		int result=0;
		String sql="delete from class where class_id=?";
		result=jdbcTemplate.update(sql, dto.getClassid());
		return result;
		}
	
	public List<ClassResponseDTO> selectAll(){
		String sql="select * from class";
		return jdbcTemplate.query(sql,(rs, rowNum) ->new ClassResponseDTO(
		rs.getString("class_id"),
		rs.getString("class_name")));
		}


	public List<ClassResponseDTO> selectCoursesByStudentId(String studentid) {
		String sql = "select class.class_name, class.class_id from selected_courses join class "
				+ "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new ClassResponseDTO(
		rs.getString("class_id"),
		rs.getString("class_name")),
		studentid);
	}
	
	public List<String> selectCidByStuid(String studentid) {
		String sql = "select class.class_name, class.class_id from selected_courses join class "
				+ "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";
		return jdbcTemplate.query(sql, (rs, rowNum) -> 
		rs.getString("class_name"),
		studentid);
	}
	
//	public List<ClassResponseDTO> selectCoursesByStudentId(String student_id) {		
//	String sql = "select class.class_name, class.class_id from selected_courses join class "
//	+ "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";
//		return jdbcTemplate.query(sql, (rs, rowNum) -> new ClassResponseDTO(
//				rs.getString("class_id"),
//				rs.getString("class_name")),
//				student_id);
//	}
}
	
//	public int checkClassname(String name) {
//		String sql = "select * from class where class_name=?";
//		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ClassResponseDTO(
//		rs.getString("class_id"),
//		rs.getString("class_name")),
//		name);
//	}
	
//	public boolean checkId(String id) {
//		String sql = "select * from class where class_id=?";
//		try {
//			PreparedStatement st = con.prepareStatement(sql);
//			st.setString(1, id);
//			ResultSet rs = st.executeQuery();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return false;
//	}
