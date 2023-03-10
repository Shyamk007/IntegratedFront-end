package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.ProjectResponse;
import com.project.dto.StudentResponse;
import com.project.pojos.Project;
import com.project.pojos.Student;

public interface StudentService {
	
	
	ApiResponse addStudentPersonalDetails(Student std,String userName);

	List<Project> getCompletedProjects();
	
	Student getStudent(String userName);
	Project getStudentProjectDetails(String userName);

	ApiResponse updateDetails(StudentResponse student, String userName);
	ApiResponse updateProjectDetails(ProjectResponse projectdetails,String userName);

	List<Student> getAllStudentDetails();

	ApiResponse deleteStudentByName(String userName);
	
}
