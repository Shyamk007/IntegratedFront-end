package com.project.config.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.filters.JWTRequestFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private JWTRequestFilter filter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		System.out.println("In Authorize Request of Url Mappers");

		http
		.cors()
		.and()
		.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		})
		.and()
		.authorizeRequests()
//		//Get all Employee details which only admin and HR Department
		.antMatchers("/stud_auth/student_signup").permitAll()
		.antMatchers("/stud_auth/change_password").permitAll()
		.antMatchers("/student/upload_srs/**").permitAll()
		.antMatchers("/student/upload_ppt/**").permitAll()
		.antMatchers("/student/upload_report/**").permitAll()
		.antMatchers("/student/getsrs/**").permitAll()
		.antMatchers("/stud_auth/student_signin").permitAll()
		.antMatchers("/student/{userName}/addPersonalDetails").permitAll()
		.antMatchers("/student/{userName}/addProjectDetails").permitAll()
		.antMatchers("/student/profile").permitAll()
		.antMatchers("/student/**").permitAll()
		.antMatchers("/student/getcompletedprojects").permitAll()
		.antMatchers("/faculty/viewAllStudents").permitAll()
		.antMatchers("/faculty_auth/**").permitAll()
		.antMatchers("/faculty/**").permitAll()
		.antMatchers("/faculty/profile/**").permitAll()
		.antMatchers("/faculty/viewallprojects").permitAll()
		.antMatchers("/faculty/updatedetails/**").permitAll()
		.antMatchers("/admin/allfaculties").permitAll()
		.antMatchers("/admin/allstudents").permitAll()
		.antMatchers("/admin/deletefaculty/**").permitAll()
		.antMatchers("/admin/deletestud/**").permitAll()
		.antMatchers("/admin/verified/**").permitAll()
		.antMatchers("/email/successful_evaluation/{prn}").permitAll()
		.antMatchers("/email/need_somework/{prn}").permitAll()
		.antMatchers("/email/forgot_password/{userName}").permitAll()
		.antMatchers("/admin_auth/admin_signin").permitAll()
		.antMatchers("/faculty_auth/faculty_signin").permitAll()
		.antMatchers("/faculty_auth/faculty_signup").permitAll()
		.antMatchers("/faculty_auth/change_password").permitAll()

//		.antMatchers("/employee/gov_id_image/{empId}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("/employee/govern_id_image/{empId}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("/employee/profile").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("/employee/name/{userName}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("/applicants/view").hasRole("ADMIN")
//		.antMatchers("/applicant/profile").hasRole("APPLICANT")
//		.antMatchers("/applicant/upload_profile_image/{appUserName}").permitAll()
//		.antMatchers("/interview/schedule_interview").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("/applicant/upload_resume/{appUserName}").hasRole("APPLICANT")
//		.antMatchers("/payslip/generate_slip/{empId}").permitAll()
//		.antMatchers("/applicant/gov_id_image/{appId}").hasRole("APPLICANT")
//		.antMatchers("/applicant/govern_id_image/{appId}").hasRole("APPLICANT")
//		.antMatchers("/applicant/prof_image/{appUserName}").permitAll()
//		.antMatchers("/applicant/{userName}/createprofile").permitAll()
//		.antMatchers("/jobdesc/createpost").permitAll()
//		.antMatchers("/jobdesc/apply/{jobId}/{appId}").permitAll()
		.antMatchers( "/auth/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
//		.antMatchers("/email/forgot_password/{userName}").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
