package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.pojos.Project;
import com.project.pojos.Student;

public interface StudentService {
	
	List<Student> getAllStudentDetails();
	Student getStudent(String userName);
//	ApiResponse addStudentDetails(Student std,String userName);
	ApiResponse addStudentPersonalDetails(Student std,String userName);
	List<Project> getCompletedProjects();
	ApiResponse deleteStudentById(Long studId);
	
}
