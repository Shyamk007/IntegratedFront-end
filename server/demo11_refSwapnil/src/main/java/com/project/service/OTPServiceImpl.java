package com.project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pojos.OTP;
import com.project.repository.OTPRepository;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {

	@Autowired
	private OTPRepository otpRepo;

	@Override
	public OTP getUserOTPbyUserName(String userName) {
		
		return otpRepo.findById(userName).get();
	}
}
