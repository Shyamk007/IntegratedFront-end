package com.project.controller;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CredentialDto;
import com.project.pojos.Credentials;
import com.project.dto.ApiResponse;
import com.project.dto.UpdatePassword;
import com.project.pojos.OTP;
import com.project.dto.LoginRequest;
import com.project.dto.LoginResponse;
import com.project.jwt_utils.JwtUtils;
import com.project.service.CredentialService;
import com.project.service.OTPService;

@RestController
@RequestMapping("/stud_auth")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentLoginController {
	
	@Autowired
	private CredentialService credService;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private OTPService otpService;
	
	
	@PostMapping("/student_signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUserName(),
				request.getPassword());
		System.out.println("In Stduent Authentcation" + authToken);
		try {
			Authentication auth = authMgr.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			System.out.println("Authentication Successful" + auth);
			return ResponseEntity.ok(new LoginResponse(jwtUtils.generateJwtToken(auth),
					credService.getUserRole(request.getUserName()).toString().substring(5)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Student authentication Failed", e);
		}
	}
	
	@PostMapping("/student_signup")
	public ResponseEntity<?> signUpStudent(@RequestBody CredentialDto cred){
		System.out.println(cred);
		Credentials studCred = mapper.map(cred, Credentials.class);
		studCred.setUserRole("ROLE_STUDENT");
		return ResponseEntity.ok(credService.createStudentCredentials(studCred));
	}
	
	
	
	@PostMapping("/change_password")
	public ResponseEntity<?> updateUserPassword(@RequestBody UpdatePassword newPassDetails) {
		OTP otp = otpService.getUserOTPbyUserName(newPassDetails.getUserName());
		
		System.out.println("DATABASE OTP :: "+ otp.getOtp().length()+" -- STUDENT OTP ::"+newPassDetails.getOtp().length());
		System.out.println("DATABASE OTP :: "+ otp.getOtp()+" -- STUDENT OTP ::"+newPassDetails.getOtp());
		System.out.println("DATABASE OTP :: "+ Integer.valueOf(otp.getOtp())+" -- STUDENT OTP :: "+Integer.valueOf(newPassDetails.getOtp()));
		
		
		if (Integer.valueOf(otp.getOtp())==Integer.parseInt(newPassDetails.getOtp())) {
			if (LocalTime.now().isBefore(otp.getTime())) {
				return ResponseEntity.ok(credService.updateUserPassword(newPassDetails.getUserName(), newPassDetails.getPassword()));
			} else {
				return ResponseEntity.status(HttpStatus.GONE).body(new ApiResponse("OTP Expired!!!"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Entered Wrong OTP"));
		}
	}
	
	
}