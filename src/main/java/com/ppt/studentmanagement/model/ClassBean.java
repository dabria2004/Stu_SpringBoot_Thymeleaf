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
public class ClassBean {
	
	private String classid;
	@NotEmpty(message="Classname cannot be blank")
	private String classname;
}
