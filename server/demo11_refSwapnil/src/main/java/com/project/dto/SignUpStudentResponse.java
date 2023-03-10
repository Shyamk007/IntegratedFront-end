package com.project.dto;

import com.project.pojos.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpStudentResponse {

	private Student std;
	private String mesg;
}
