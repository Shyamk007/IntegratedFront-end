package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@DeleteMapping("/deletefaculty/{factId}")
	public ResponseEntity<?> deleteFaculty(@PathVariable Long factId) {
		return ResponseEntity.ok(factService.deleteFacultyById(factId));
	}

	@DeleteMapping("/deletestud/{studId}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long studId) {
		return ResponseEntity.ok(studService.deleteStudentById(studId));
	}

}
