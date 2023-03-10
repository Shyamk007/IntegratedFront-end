package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.pojos.Credentials;
import com.project.pojos.Project;
import com.project.pojos.Student;
import com.project.repository.CredentialRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studRepo;

	@Autowired
	private CredentialRepository credRepo;
	
	@Autowired
	private ProjectRepository projRepo;

	@Override
	public List<Student> getAllStudentDetails() {
		// TODO Auto-generated method stub
		return studRepo.findAll();
	}

	@Override
	public Student getStudent(String userName) {
		// TODO Auto-generated method stub
		return studRepo.findByStudentCredentialsUserName(userName).get();
	}

	@Override
	public ApiResponse addStudentPersonalDetails(Student std, String userName) {
		// TODO Auto-generated method stub
//		System.out.println(std.toString());
	
		Credentials cred = credRepo.findByUserName(userName).get();

		cred.setStudentPRN(std);
//		System.out.println(cred.toString());
//		studRepo.save(std);
		// if we use studRepo.save(std) it will run the hibernate query twice hence we
		// have used
		credRepo.save(cred);
		return new ApiResponse("Student Personal Details Added Successfully !!!");
	}

	@Override
	public List<Project> getCompletedProjects() {
		// TODO Auto-generated method stub
		return projRepo.findAllByStatus("COMPLETED");
	}

	@Override
	public ApiResponse deleteStudentById(Long studId) {
		// TODO Auto-generated method stub
		studRepo.deleteById(studId);
		return new ApiResponse("Student of Id : "+ studId + " deleted Sucessfully");
	}


}
