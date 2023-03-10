package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.FacultyResponse;
import com.project.dto.LoginRequest;
import com.project.dto.ProjectEvaluationDto;
import com.project.pojos.Faculty;

public interface FacultyService {
	
	boolean isVerifiedAccount(LoginRequest fact);
	
	List<Faculty> getAllFaculties();
	
	ApiResponse deleteFacultyById(Long factId);
	
	ApiResponse addFacultyDetails(Faculty fact, String userName);
	
	ApiResponse updateDetails(FacultyResponse fact, String userName);
	
	ApiResponse evaluateProject(ProjectEvaluationDto prj, Long stdId);
}

