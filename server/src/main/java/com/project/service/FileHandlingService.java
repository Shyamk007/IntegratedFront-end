package com.project.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.project.dto.ApiResponse;

public interface FileHandlingService {
	
	ApiResponse uploadStudentSrs(String userName,MultipartFile pdfFile) throws IOException;
	ApiResponse uploadStudentPpt(String userName,MultipartFile pptFile) throws IOException;
	ApiResponse uploadStudentReport(String userName,MultipartFile reportFile) throws IOException;
	byte[] getStudentSrs(String userName) throws IOException;
	byte[] getStudentPpt(String userName) throws IOException;
	byte[] getStudentReport(String userName) throws IOException;
	

}
