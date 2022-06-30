package com.ppt.studentmanagement.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentResponseDTO {
	private String studentid;
	private String studentname;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private List<String> attendCourses;
	public StudentResponseDTO(String studentid, String studentname, String dob, String gender, String phone,
			String education) {
		super();
		this.studentid = studentid;
		this.studentname = studentname;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.education = education;
	}
}
