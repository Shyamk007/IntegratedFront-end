package com.project.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.dto.ProjectResponse;
import com.project.dto.StudentResponse;
import com.project.pojos.Project;
import com.project.pojos.Student;
import com.project.service.FileHandlingService;
import com.project.service.ProjectService;
import com.project.service.StudentService;



@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	@Autowired
	private StudentService studService;

	@Autowired
	private ProjectService prjService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FileHandlingService fileService;

	public StudentController() {
		System.out.println("In default constructor of " + getClass());
	}

	
	@PostMapping(value = "/upload_srs/{stdUserName}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadProjectSrs(@RequestParam MultipartFile srsFile,
			@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.uploadStudentSrs(stdUserName, srsFile), HttpStatus.CREATED);
	}

	@PostMapping(value = "/upload_ppt/{stdUserName}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadProjectPpt(@RequestParam MultipartFile pptFile,
			@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.uploadStudentPpt(stdUserName, pptFile), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/upload_report/{stdUserName}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadProjectReport(@RequestParam MultipartFile reportFile,
			@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.uploadStudentReport(stdUserName, reportFile), HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/get_srs/{stdUserName}", produces = { MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<?> getProjectSrs(@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.getStudentSrs(stdUserName), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/get_ppt/{stdUserName}", produces = { MediaType.APPLICATION_PDF_VALUE})
	// change the media type to produces = MediaType.APPLICATION_POWERPOINT_VALUE for uploading the specific files about the ppt format
	// we also have to ask instead of seeing the file like this how can i directly download the file by clicking the on the see ppt button
	public ResponseEntity<?> getProjectPpt(@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.getStudentPpt(stdUserName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get_report/{stdUserName}", produces = { MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<?> getProjectReport(@PathVariable String stdUserName) throws IOException {
		return new ResponseEntity<>(fileService.getStudentReport(stdUserName), HttpStatus.OK);
	}
	
	

	@PostMapping("/{userName}/addPersonalDetails")
	public ResponseEntity<?> addStudentPersonalDetails(@PathVariable String userName,@RequestBody StudentResponse std)
	{
		Student stdDetails = mapper.map(std, Student.class);
		return ResponseEntity.ok(studService.addStudentPersonalDetails(stdDetails, userName));
	}
	
	@PutMapping("/updatePersonalDetails/{userName}")
	public ResponseEntity<?> updateStudentDetails(@RequestBody StudentResponse student,@PathVariable String userName){
		return ResponseEntity.ok(studService.updateDetails(student,userName));
	}
	
	
	@PostMapping("/{userName}/addProjectDetails")
	public ResponseEntity<?> addStudentProjectDetails(@PathVariable String userName,@RequestBody ProjectResponse prj)
	{
		Project prjDetails = mapper.map(prj, Project.class);
		return ResponseEntity.ok(prjService.addStudentProjectDetails(prjDetails,userName));
	}
	
	@PutMapping("/updateProjectDetails/{userName}")
	public ResponseEntity<?> updateProjectDetails(@RequestBody ProjectResponse project,@PathVariable String userName){
		return ResponseEntity.ok(studService.updateProjectDetails(project, userName));
	}
	
	@GetMapping("/profile/personal/{userName}")
	public Student getStudentPersonalDetails(@RequestParam String userName) {
		return studService.getStudent(userName);
	}
	
	@GetMapping("/profile/project/{userName}")
	public Project getStudentProjectDetails(@RequestParam String userName) {
		return studService.getStudentProjectDetails(userName);
	}
	
	@GetMapping("/getcompletedprojects")
	public ResponseEntity<?> getCompletedProjects(){
	
		return ResponseEntity.ok(studService.getCompletedProjects());
	}
	
	@GetMapping("/profile/prn")
	public ResponseEntity<?> getProjectByPRN(@RequestParam Long prn){
		return ResponseEntity.ok(prjService.findByStdID(prn));
	}
	
	//also have to write the update project details and 
	


}
