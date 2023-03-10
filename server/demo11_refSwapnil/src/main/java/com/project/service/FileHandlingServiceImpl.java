package com.project.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.customexceptions.ResourceNotFoundException;
import com.project.dto.ApiResponse;
import com.project.pojos.Project;
import com.project.pojos.Student;
import com.project.repository.ProjectRepository;
import com.project.repository.StudentRepository;

@Service
@Transactional
public class FileHandlingServiceImpl implements FileHandlingService {

	@Value("${student.srs.upload.folder}")
	private String studentSrsFolder;

	@Value("${student.ppt.upload.folder}")
	private String studentPptFolder;

	@Value("${student.report.upload.folder}")
	private String studentReportFolder;

	@Autowired
	private ProjectService prjservice;

	@Autowired
	private ProjectRepository prjRepo;

	@Autowired
	private StudentRepository studRepo;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + studentSrsFolder);
		System.out.println("in myInit " + studentPptFolder);
		System.out.println("in myInit " + studentReportFolder);

		File studentSrsFilePath = new File(studentSrsFolder);
		File studentPptFilePath = new File(studentPptFolder);
		File studentReportFilePath = new File(studentReportFolder);
//		File projectSrsFilePath =new File(projectSrsFolder);

		if (!studentSrsFilePath.exists()) {
			studentSrsFilePath.mkdirs();
		} else if (!studentPptFilePath.exists()) {
			studentPptFilePath.mkdirs();
		} else if (!studentReportFilePath.exists()) {
			studentReportFilePath.mkdirs();
		} else {
			System.out.println("Folders Already Exists !!!");
		}
	}

	@Override
	public ApiResponse uploadStudentSrs(String userName, MultipartFile pdfFile) throws IOException {
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		String targetPath = studentSrsFolder + File.separator + "srs" + std.getPRN() + "."
				+ pdfFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Student SRS File Path : " + targetPath);
		Files.copy(pdfFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		System.out.println(std.getPRN());
		Project prj = prjservice.findByStdID(std.getPRN());
		prj.setSrsPath(targetPath);
		System.out.println(prj.getSrsPath());
		prjRepo.save(prj);
		return new ApiResponse("SRS Uploaded Sucessfully !!!");

	}

	@Override
	public ApiResponse uploadStudentPpt(String userName, MultipartFile pptFile) throws IOException {
		
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		
		String targetPath = studentPptFolder + File.separator + "ppt" + std.getPRN() + "."
				+ pptFile.getOriginalFilename().split("\\.")[1];
		
		System.out.println("Student PPT File Path : " + targetPath);
		
		Files.copy(pptFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		
		Project prj = prjservice.findByStdID(std.getPRN());
		
		prj.setPptPath(targetPath);
		
		prjRepo.save(prj);
		return new ApiResponse("Ppt Uploaded Sucessfully !!!");
	}

	@Override
	public ApiResponse uploadStudentReport(String userName, MultipartFile reportFile) throws IOException {
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		String targetPath = studentReportFolder + File.separator + "report" + std.getPRN() + "."
				+ reportFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Student REPORT File Path : " + targetPath);
		Files.copy(reportFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		// check later
		Project prj = prjservice.findByStdID(std.getPRN());
		prj.setReportPath(targetPath);
		prjRepo.save(prj);
		return new ApiResponse("Report Uploaded Sucessfully !!!");
	}

	@Override
	public byte[] getStudentSrs(String userName) throws IOException {
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		// check later
		String path = prjservice.findByStdID(std.getPRN()).getSrsPath();
		if (path == null) {
			throw new ResourceNotFoundException("SRS does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public byte[] getStudentPpt(String userName) throws IOException {
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		// check later
		String path = prjservice.findByStdID(std.getPRN()).getPptPath();
		if (path == null) {
			throw new ResourceNotFoundException("PPT does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public byte[] getStudentReport(String userName) throws IOException {
		Student std = studRepo.findByStudentCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Student PRN !!!!! "));
		// check later
		String path = prjservice.findByStdID(std.getPRN()).getReportPath();
		if (path == null) {
			throw new ResourceNotFoundException("Report does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

}
