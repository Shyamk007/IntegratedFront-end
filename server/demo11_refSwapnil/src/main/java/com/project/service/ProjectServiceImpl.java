package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customexceptions.ResourceNotFoundException;
import com.project.dto.ApiResponse;
import com.project.pojos.Credentials;
import com.project.pojos.Project;
import com.project.pojos.Student;
import com.project.repository.CredentialRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.StudentRepository;
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private StudentRepository studRepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	
	@Override
	public Project findByStdID(Long Id) {
		// TODO Auto-generated method stub
		return projectrepo.findByStdPRN(Id);
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return projectrepo.findAll();
	}

	@Override
	public ApiResponse addStudentProjectDetails(Project prj, String userName) {
		
//		Credentials cred = credRepo.findByUserName(userName).get();
		// get student object by username 
//		Student stud = studRepo.findByStudentCredentialsUserName(userName).orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		
//		stud.setStudentProjectDetails(prj);

//		System.out.println(stud.toString());
//		proj.updateProject(prj);
//		projectrepo.save(proj);
		// get proj id by student PRN
		
		Student stud = studRepo.findByStudentCredentialsUserName(userName).get();
		System.out.println(prj);
		stud.setStudentProjectDetails(prj);
//		System.out.println(stud.toString());
		studRepo.save(stud);
			
		return new ApiResponse("Student Project Details Added Successfully !!!");
		
	}
	
}
