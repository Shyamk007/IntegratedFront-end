package com.project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.project.customexceptions.ResourceNotFoundException;
import com.project.pojos.Credentials;
import com.project.pojos.Faculty;
import com.project.pojos.OTP;
import com.project.pojos.OTPGenerator;
import com.project.pojos.Student;
import com.project.repository.AdminRepository;
import com.project.repository.CredentialRepository;
import com.project.repository.EmailMessageRepository;
import com.project.repository.FacultyRepository;
import com.project.repository.OTPRepository;
import com.project.repository.StudentRepository;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private CredentialRepository credRepo;

	@Autowired
	private OTPRepository oTPRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private StudentRepository studRepo;

	@Autowired
	private FacultyRepository facRepo;

	@Override
	public SimpleMailMessage sendPasswordChangeOTPMail(String userName) {
		Credentials role = credRepo.findById(userName)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Name"));
		String otp = OTPGenerator.OTP();
		OTP otpData = new OTP(userName, otp);
		oTPRepo.save(otpData);
		SimpleMailMessage mesg = new SimpleMailMessage();
		if (role.getUserRole().toString().equalsIgnoreCase("ROLE_STUDENT")) {
			Student stud = studRepo.findByStudentCredentialsUserName(userName)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid User Name"));
			mesg.setTo(stud.getEmail());
			mesg.setSubject("OTP for password change request");
			mesg.setText(
					EmailMessageRepository.passwordChangeOTPEmail(stud.getFirstName() + "" + stud.getLastName(), otp));
			return mesg;
		}

		else {
			Faculty fac = facRepo.findByFacultyCredentialsUserName(userName)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid User Name"));
			mesg.setTo(fac.getEmail());
			mesg.setSubject("OTP for password change request");
			mesg.setText(
					EmailMessageRepository.passwordChangeOTPEmail(fac.getFirstName() + " " + fac.getLastName(), otp));
			return mesg;
		}
	}

	@Override
	public SimpleMailMessage sendSuccessfullyEvaluatedProjectEmail(Long prn) {

		SimpleMailMessage mesg = new SimpleMailMessage();

		Student stud = studRepo.findByPRN(prn)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid PRN"));

		mesg.setTo(stud.getEmail());
		mesg.setSubject("Regarding Successful Evaluation of Project");
		mesg.setText(EmailMessageRepository
				.successfullyEvaluatedProjectEmail(stud.getFirstName() + " " + stud.getLastName()));

		return mesg;
	}

	@Override
	public SimpleMailMessage sendProjectNeedSomeWork(Long prn) {
		SimpleMailMessage mesg = new SimpleMailMessage();

		Student stud = studRepo.findByPRN(prn)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid PRN"));

		mesg.setTo(stud.getEmail());
		mesg.setSubject("Regarding Re-Evaluation of Project");
		mesg.setText(EmailMessageRepository
				.projectNeedSomeWork(stud.getFirstName() + " " + stud.getLastName()));

		return mesg;
		
	}
}
