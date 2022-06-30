package com.ppt.studentmanagement.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ppt.studentmanagement.dto.*;

@Repository
public class UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int insertData(UserRequestDTO dto) {
		int result=0;
		String sql="insert into user(user_id,user_name,user_email,user_password,user_conpassword,user_role) values (?,?,?,?,?,?)";
		result=jdbcTemplate.update(sql,dto.getUserid(), dto.getUsername(), dto.getUseremail(), dto.getPassword(), dto.getConpassword(), dto.getRole());
		return result;
		}
	
	public int updateData(UserRequestDTO dto) {
		int result=0;
		String sql="update user set user_name=?,user_email=?,user_password=?,user_conpassword=?,user_role=? where user_id=?";
		result=jdbcTemplate.update(sql,dto.getUsername(), dto.getUseremail(), dto.getPassword(), dto.getConpassword(), dto.getRole(), dto.getUserid());
		return result;
		}
	
	public int deleteData(String id) {
		int result=0;
		String sql="delete from user where user_id=?";
		result=jdbcTemplate.update(sql, id);
		return result;
		}
	
	public UserResponseDTO selectUserById(UserRequestDTO dto) {
		String sql="select * from user where user_id=?";
		return jdbcTemplate.queryForObject(sql,(rs, rowNum) ->new UserResponseDTO(
		rs.getString("user_id"),
		rs.getString("user_name"),
		rs.getString("user_email"),
		rs.getString("user_password"),
		rs.getString("user_conpassword"),
		rs.getString("user_role")),
		dto.getUserid());
		}
	
	public UserResponseDTO selectUserByName(String name) {
		String sql="select * from user where user_name=?";
		return jdbcTemplate.queryForObject(sql,(rs, rowNum) ->new UserResponseDTO(
		rs.getString("user_id"),
		rs.getString("user_name"),
		rs.getString("user_email"),
		rs.getString("user_password"),
		rs.getString("user_conpassword"),
		rs.getString("user_role")),
		name);
		}
	
	public List<UserResponseDTO> selectUserListById(String id) {
		String sql="select * from user where user_id like ?";
		return jdbcTemplate.query(sql,(rs, rowNum) ->new UserResponseDTO(
		rs.getString("user_id"),
		rs.getString("user_name"),
		rs.getString("user_email"),
		rs.getString("user_password"),
		rs.getString("user_conpassword"),
		rs.getString("user_role")),
		"%" + id + "%");
		}
	
	public List<UserResponseDTO> selectUserListByName(String name) {
		String sql="select * from user where user_name like ?";
		return jdbcTemplate.query(sql,(rs, rowNum) ->new UserResponseDTO(
		rs.getString("user_id"),
		rs.getString("user_name"),
		rs.getString("user_email"),
		rs.getString("user_password"),
		rs.getString("user_conpassword"),
		rs.getString("user_role")),
		"%" + name + "%");
		}
	
	public List<UserResponseDTO> selectAll(){
		String sql="select * from user";
		return jdbcTemplate.query(sql,(rs, rowNum) ->new UserResponseDTO(
				rs.getString("user_id"),
				rs.getString("user_name"),
				rs.getString("user_email"),
				rs.getString("user_password"),
				rs.getString("user_conpassword"),
				rs.getString("user_role")));
		}

	public List<UserResponseDTO> selectUserByIdOrName(String id, String name) {
		String sql="select * from user where user_id like ? or user_name like ?";
		return jdbcTemplate.query(sql,(rs, rowNum) ->new UserResponseDTO(
		rs.getString("user_id"),
		rs.getString("user_name"),
		rs.getString("user_email"),
		rs.getString("user_password"),
		rs.getString("user_conpassword"),
		rs.getString("user_role")),
		"%" + id + "%",
		"%" + name + "%");
		}
	
	public List<UserResponseDTO> findByIdOrName(String id, String name) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", "%" + id + "%");
        mapSqlParameterSource.addValue("name", "%" + name + "%");

        return namedParameterJdbcTemplate.query(
                "select * from user where user_id like ? or user_name like ?",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new UserResponseDTO(
                        		rs.getString("user_id"),
                				rs.getString("user_name"),
                				rs.getString("user_email"),
                				rs.getString("user_password"),
                				rs.getString("user_conpassword"),
                				rs.getString("user_role")
                        )
        );
    }
	
	public boolean checkLogin(String email, String password) {
        String sql = "select count(*) from user where binary email=? && binary password=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        return count != null && count > 0;
    }
	
//	public List<UserResponseDTO> selectUserByIdOrName(String id, String name){
//	String sql = "select * form user where user_id like = ? or user_name like = ?";
//	return jdbcTemplate.query(sql,
//			new Object[]{"%" + id + "%", "%" + name + "%"},
//			(rs, rowNum) -> new UserResponseDTO(
//			rs.getString("user_id"),
//			rs.getString("user_name"),
//			rs.getString("user_email"),
//			rs.getString("user_password"),
//			rs.getString("user_conpassword"),
//			rs.getString("user_role")));
//}
}
