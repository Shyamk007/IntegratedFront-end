package com.project.dto;

import com.project.pojos.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
	
	
//	private Integer proj_Id;
	private String title;
	private String description;
//	private String projectGuide;
	//private Status status=Status.NEW;
	
}
