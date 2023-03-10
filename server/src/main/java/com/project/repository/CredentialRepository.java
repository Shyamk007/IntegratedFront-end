package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials, String>{
	
	Optional<Credentials> findByUserNameAndPassword(String userName, String password);
	Optional<Credentials> findByUserName(String userName );
	Optional<Credentials> deleteByUserName(String userName );
}
