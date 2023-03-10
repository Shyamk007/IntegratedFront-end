package com.project.dto;

import com.project.pojos.Faculty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpFacultyResponse {

	private Faculty faculty;
	private String mesg;
}
