package com.ppt.studentmanagement.dto;

import lombok.*;

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
