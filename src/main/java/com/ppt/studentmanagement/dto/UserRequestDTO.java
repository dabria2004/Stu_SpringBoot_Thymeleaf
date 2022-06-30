package com.ppt.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
	String userid;
	String username;
	String useremail;
	String password;
	String conpassword;
	String role;
}
