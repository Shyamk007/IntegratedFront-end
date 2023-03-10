package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ApiResponse;
import com.project.dto.FacultyResponse;
import com.project.dto.LoginRequest;
import com.project.dto.ProjectEvaluationDto;
import com.project.pojos.Account_Status;
import com.project.pojos.Credentials;
import com.project.pojos.Faculty;
import com.project.pojos.Project;
import com.project.repository.CredentialRepository;
import com.project.repository.FacultyRepository;
import com.project.repository.ProjectRepository;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

	
	@Autowired
	private FacultyRepository factRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProjectRepository prjRepo;

	@Autowired
	private CredentialRepository credRepo;
	
	@Override
	public boolean isVerifiedAccount(LoginRequest fact) {
		Faculty faculty = factRepo.findByFacultyCredentialsUserName(fact.getUserName()).get(); 
		if(faculty.getAccStatus().equals(Account_Status.VERIFIED))
		{
		return true;
		}
		else
		{
			return false;
		}
	
	}

	@Override
	public List<Faculty> getAllFaculties() {
		// TODO Auto-generated method stub
		return factRepo.findAll();
	}

	@Override
	public ApiResponse deleteFacultyById(Long factId) {
		// TODO Auto-generated method stub
		factRepo.deleteById(factId);
		return new ApiResponse("Deleted Faculty of Id : "+factId + " Sucessfully");
	}

	@Override
	public ApiResponse updateDetails(FacultyResponse fact,String userName) {
		// TODO Auto-generated method stub
		Faculty faculty = factRepo.findByFacultyCredentialsUserName(userName).get();
		faculty.setEmail(fact.getEmail());
		faculty.setExperience(fact.getExperience());
		faculty.setQualification(fact.getQualification());
		faculty.setFirstName(fact.getFirstName());
		faculty.setLastName(fact.getLastName());
//		System.out.println("Faculty Details"+faculty);
//		factRepo.save(faculty);
		return new ApiResponse("Updated Details");
	}

	@Override
	public ApiResponse evaluateProject(ProjectEvaluationDto prj,Long stdId) {
		// TODO Auto-generated method stub
		Project proj = prjRepo.findByStdPRN(stdId);
		proj.setFeedback(prj.getFeedback());
		proj.setRatings(prj.getRatings());
		proj.setStatus(prj.getStatus());
		return new ApiResponse("Project Evaluated");
	}

	@Override
	public ApiResponse addFacultyDetails(Faculty fact, String userName) {
		// TODO Auto-generated method stub
		Credentials cred = credRepo.findByUserName(userName).get();
		cred.setFacultyId(fact);
		credRepo.save(cred);
		return new ApiResponse("Faculty Added");
	}	
}


