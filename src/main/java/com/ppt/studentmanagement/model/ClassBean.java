package com.ppt.studentmanagement.model;

import javax.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassBean {
	@NotEmpty
	private String classid;
	@NotEmpty(message="Classname cannot be blank")
	private String classname;
}
