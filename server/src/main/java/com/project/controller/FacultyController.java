package com.project.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.project.dto.FacultyResponse;
import com.project.dto.ProjectEvaluationDto;
import com.project.pojos.Faculty;
import com.project.pojos.Project;
import com.project.pojos.Student;
import com.project.service.FacultyService;
import com.project.service.ProjectService;
import com.project.service.StudentService;


@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "http://localhost:3000")
public class FacultyController {

	@Autowired
	private StudentService studeService;
	
	@Autowired
	private FacultyService factService;
	
	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private ModelMapper mapper;
	
	// while i was testing the controller of sending mail and the error was daily sending quota exceeded
	// i searched about it on google it showed me this in India, the Gmail daily sending limit resets at 1:30 PM IST the following day.
	// have to check this error too
	// view all projects is also showing the faculty details which shouldnt be there 
	// i guess the viewallstudents is also showing the details of faculty

	@GetMapping("/viewallprojects")
	public List<Project> getAllProjects() {
		return prjService.getAllProjects();
	}
	
	@GetMapping("/viewAllStudents")
	public List<Student> getAllStudents(){
		return studeService.getAllStudentDetails();
	}
	
	@GetMapping("/profile/{userName}")
	public Faculty getPersonalDetails(@RequestParam String userName) {
		return factService.getFacultyDetails(userName);
	}
	
	@PutMapping("/updatedetails/{userName}")
	public ResponseEntity<?> updateFacultyDetails(@RequestBody FacultyResponse faculty,@PathVariable String userName){
		
		return ResponseEntity.ok(factService.updateDetails(faculty,userName));
	}
	
	@PostMapping("/addfaculty/{userName}")
	public ResponseEntity<?> addFacultyDetails(@RequestBody FacultyResponse faculty,@PathVariable String userName){
		
		Faculty fact  = mapper.map(faculty,Faculty.class);
		System.out.println(fact.toString());
		return ResponseEntity.ok(factService.addFacultyDetails(fact,userName));
	}
	
	@PutMapping("/evaluateproject/{prn}")
	public ResponseEntity<?> evaluateProject(@RequestBody ProjectEvaluationDto prj,@PathVariable Long prn){
		return ResponseEntity.ok(factService.evaluateProject(prj, prn));
	}
	
	
	
	
	
	
	
}
