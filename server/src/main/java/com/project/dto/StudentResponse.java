package com.project.dto;

import java.time.LocalDate;

import com.project.pojos.Batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponse {

	private String firstName;
	private String lastName;
	private Long PRN;
	private Long RollNo;
	private String email;
	private LocalDate SubmitDate;
	private Batch batch;
	
	
}
