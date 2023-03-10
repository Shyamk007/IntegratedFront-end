package com.project.dto;

import com.project.pojos.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEvaluationDto {

	private String feedback;
	private int ratings;
	private Status status;
}
