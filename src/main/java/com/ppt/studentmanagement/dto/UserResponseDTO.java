package com.ppt.studentmanagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
	String userid;
	String username;
	String useremail;
	String password;
	String conpassword;
	String role;
}
