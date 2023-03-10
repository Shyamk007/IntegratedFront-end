package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.pojos.Credentials;
import com.project.repository.CredentialRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CredentialRepository credRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credentials user = credRepo.findByUserName(username).get();
		return new CustomUserDetails(user);
	}
	// dep : User Repo
//	@Autowired
//	private CredentialRepository credRepo;
//This method will be auto invoked by spring sec : DaoAuthProvider
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// get user details from DB by user name
//		Credentials user = credRepo.findByUserName(userName)
//				.orElseThrow(() -> new UsernameNotFoundException("Invalid UserName!!!!!!!!!!"));
		//=> email verifed --now simply ret user details lifted form DB to the caller
//		return new CustomUserDetails(user);
//	}

}
