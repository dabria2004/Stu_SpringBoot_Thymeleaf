package com.ppt.studentmanagement.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBean {
	
	String userid;
	@NotEmpty
	String username;
	@NotEmpty
	String useremail;
	@NotEmpty
	String password;
	@NotEmpty
	String conpassword;
	@NotEmpty
	String role;
}
