package com.ppt.studentmanagement.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ppt.studentmanagement.dto.*;

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
	
//	public int checkClassname(String name) {
//		String sql = "select * from class where class_name=?";
//		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ClassResponseDTO(
//		rs.getString("class_id"),
//		rs.getString("class_name")),
//		name);
//	}
	
//	public ArrayList<ClassResponseDTO> selectAll(){
//		ArrayList<ClassResponseDTO> list=new ArrayList();
//		String sql="select * from class";		
//		try {
//		PreparedStatement ps=con.prepareStatement(sql);
//		ResultSet rs=ps.executeQuery();
//		while(rs.next()) {
//			ClassResponseDTO res=new ClassResponseDTO();
//			res.setClassid(rs.getString("class_id"));
//			res.setClassname(rs.getString("class_name"));
//			list.add(res);
//}
//	} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Database error in selecting class data.");
//		}
//		return list;
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
}
