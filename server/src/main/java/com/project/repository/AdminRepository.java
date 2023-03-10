package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByAdminCredentialsUserName(String userName);
	

}
