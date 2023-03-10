package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.pojos.Credentials;
import com.project.pojos.Role;

public interface CredentialService {
	
	List<Credentials> getAllCredentials();
	Role getUserRole(String userName);
	ApiResponse createStudentCredentials(Credentials studCred);
	ApiResponse createFacultyCredentials(Credentials facultyCred);
	ApiResponse updateUserPassword(String userName,String password);
	
	

}
