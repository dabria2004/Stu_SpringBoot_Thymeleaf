package com.ppt.studentmanagement.model;

import java.util.List;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentBean {
	@NotEmpty
	private String studentid;
	@NotEmpty
	private String studentname;
	@NotEmpty
	private String dob;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String education;
	@NotEmpty
	private List<String> attendCourses;
	public StudentBean(@NotEmpty String studentid, @NotEmpty String studentname, @NotEmpty String dob,
			@NotEmpty String gender, @NotEmpty String phone, @NotEmpty String education) {
		super();
		this.studentid = studentid;
		this.studentname = studentname;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.education = education;
	}
	
	
}
