package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.pojos.Project;
import com.project.pojos.Student;

public interface ProjectService {

	Project findByStdID(Long Id);
	List<Project> getAllProjects();
	ApiResponse addStudentProjectDetails(Project prj,String userName);
}
