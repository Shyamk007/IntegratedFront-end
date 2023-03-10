package com.project.repository;

public class EmailMessageRepository {

	public static String passwordChangeOTPEmail(String name, String oTP) {
		String message = "Dear "+name+",\r\n"
				+"Please use the below One Time Password (OTP) to reset your password. This will be valid for 15 min only.\r\n"
				+ "One Time Password (OTP) : "+oTP+"\r\n"
				+"If you are unable to change the password within 15 minutes of OTP generation, please click on \"Forgot Password\" and continue with the same process again.\r\n"
				+ "Wish you all the best!\r\n"
				+ "Note: Use Chrome browser for best experience. \r\n"
				+ "Best Regards,\r\n"
				+ "Project Diary Team\r\n"
				+ "IACSD Student Portal\r\n"
				;
		return message;
	}
	
	public static String successfullyEvaluatedProjectEmail(String name) {
		String message = "Hi "+name+",\r\n"
				+"I am pleased to inform you that your project submission has been successfully\r\n"
				+"evaluated and has been selected for the final project review. .\r\n"
				+"Your project has met all of the required criteria and has demonstrated \r\n"
				+"a thorough understanding of the project topic..\r\n"
				+"We congratulate you on this achievement and wish you the best of luck for the final project review.\r\n"
				+"Thank you for participating in the project and we appreciate your contributions.\r\n"
				+"Best regards,\r\n"
				+ "Project Diary Team\r\n"
				+ "IACSD Student Portal\r\n";
		return message;
	}
	
	public static String projectNeedSomeWork(String name) {
		String message = "Hi "+name+",\r\n"
				+"I hope this email finds you well. I am writing to inform you that your project submission"
				+ "did not meet the required criteria for acceptance.\r\n"
				+"To help you improve your project, we have attached a detailed feedback form outlining the areas that need improvement.\r\n"
				+"Please take the time to review the feedback and make the necessary changes to your project before resubmitting it.\r\n"
				+"We encourage you to take advantage of the resources available to you,\r\n"
				+"such as consulting with your instructor or seeking guidance from the project team.\r\n"
				+"Thank you for your understanding \r\n"
				+"and we look forward to receiving your revised project submission.\r\n"
				+"Best regards\r\n"
				+ "Project Diary Team\r\n"
				+ "IACSD Student Portal\r\n";
		return message;
	}
}
