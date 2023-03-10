package com.project.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	SimpleMailMessage sendPasswordChangeOTPMail(String userName);
	SimpleMailMessage sendSuccessfullyEvaluatedProjectEmail(String userName);
	SimpleMailMessage sendProjectNeedSomeWork(String userName);

}
