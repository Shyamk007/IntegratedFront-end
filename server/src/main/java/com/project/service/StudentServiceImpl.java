package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.dto.ProjectResponse;
import com.project.dto.StudentResponse;
import com.project.pojos.Account_Status;
import com.project.pojos.Credentials;
import com.project.pojos.Project;
import com.project.pojos.Status;
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
	public Student getStudent(String userName) {
		// TODO Auto-generated method stub
		return studRepo.findByStudentCredentialsUserName(userName).get();
	}
	

	@Override
	public ApiResponse addStudentPersonalDetails(Student std, String userName) {
			Credentials cred = credRepo.findByUserName(userName).get();
		cred.setStudentPRN(std);
		credRepo.save(cred);
		return new ApiResponse("Student Personal Details Added Successfully !!!");
	}

	@Override
	public List<Project> getCompletedProjects() {
		return projRepo.findAllByStatus(Status.COMPLETED);
	}
	
	@Override
	public Project getStudentProjectDetails(String userName) {
		Student stud = studRepo.findByStudentCredentialsUserName(userName).get();
		// here i got the prn from the std repo and applied that prn in the project repo to get that specific project
		return projRepo.findByStdPRN(stud.getPRN());
	}

	@Override
	public ApiResponse deleteStudentByName(String userName) {
		credRepo.deleteByUserName(userName);
		return new ApiResponse("Student of userName : "+ userName + " deleted Sucessfully");
	}

	@Override
	public ApiResponse updateDetails(StudentResponse stud, String userName) {
		Student student = studRepo.findByStudentCredentialsUserName(userName).get();
		student.setFirstName(stud.getFirstName());
		student.setLastName(stud.getLastName());
		student.setRollNo(stud.getRollNo());
		student.setEmail(stud.getEmail());
		student.setSubmitDate(stud.getSubmitDate());
		student.setBatch(stud.getBatch());
		
		return new ApiResponse(userName+"Personal Details Updated");
	}

	@Override
	public ApiResponse updateProjectDetails(ProjectResponse projectdetails, String userName) {
		
		Student student = studRepo.findByStudentCredentialsUserName(userName).get();
		Project prj = projRepo.findByStdPRN(student.getPRN());
		prj.setTitle(projectdetails.getTitle());
		prj.setDescription(projectdetails.getDescription());
		
		return new ApiResponse(userName +"Project Details Updated");
	}


	//this method is for admin and the faculty
	@Override
	public List<Student> getAllStudentDetails() {
		return studRepo.findAll();
	}
	
}
