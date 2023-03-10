package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.FacultyService;
import com.project.service.StudentService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private FacultyService factService;

	@Autowired
	private StudentService studService;

	@GetMapping("/allfaculties")
	public ResponseEntity<?> getAllFaculties() {
		return ResponseEntity.ok(factService.getAllFaculties());
	}

	@GetMapping("/allstudents")
	public ResponseEntity<?> getAllStudents() {
		return ResponseEntity.ok(studService.getAllStudentDetails());
	}

	@DeleteMapping("/deletefaculty/{userName}")
	public ResponseEntity<?> deleteFaculty(@PathVariable String userName) {
		return ResponseEntity.ok(factService.deleteByFacultyByName(userName));
	}

	//this method is not working correctly
	@DeleteMapping("/deletestud/{userName}")
	public ResponseEntity<?> deleteStudent(@PathVariable String userName) {
		return ResponseEntity.ok(studService.deleteStudentByName(userName));
	}
	
	
	@PutMapping("/verified/{factId}")
	public ResponseEntity<?> verifyAccount(@PathVariable Long factId){
		return ResponseEntity.ok(factService.verifyAccount(factId));
	}
}
