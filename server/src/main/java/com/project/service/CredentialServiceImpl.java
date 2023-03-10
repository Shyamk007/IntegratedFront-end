package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.pojos.Credentials;
import com.project.pojos.Role;
import com.project.repository.CredentialRepository;
import com.project.repository.OTPRepository;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private CredentialRepository credRepo;
	
	@Autowired
	private OTPRepository otpRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<Credentials> getAllCredentials() {
		// TODO Auto-generated method stub
		return credRepo.findAll();
	}

	@Override
	public Role getUserRole(String userName) {
		// TODO Auto-generated method stub
		return credRepo.findByUserName(userName).get().getUserRole();
	}

	@Override
	public ApiResponse createStudentCredentials(Credentials studCred) {
		// TODO Auto-generated method stub
		studCred.setPassword(encoder.encode(studCred.getPassword()));
		credRepo.save(studCred);
		return new ApiResponse("Credentials Saved Successfully !!");
	}
	
	@Override
	public ApiResponse createFacultyCredentials(Credentials facultyCred) {
		// TODO Auto-generated method stub
		facultyCred.setPassword(encoder.encode(facultyCred.getPassword()));
		credRepo.save(facultyCred);
		return new ApiResponse("Credentials Saved Successfully !!");
	}
	

	@Override
	public ApiResponse updateUserPassword(String userName, String password) {
		// TODO Auto-generated method stub
		Credentials cred = credRepo.findByUserName(userName).get();
		System.out.println("Password   ------  "+password);
		cred.setPassword(encoder.encode(password));
		credRepo.save(cred);
		otpRepo.deleteById(userName);
		return new ApiResponse("Updated Password Sucessfully");
	}

	
	
	
	
}
