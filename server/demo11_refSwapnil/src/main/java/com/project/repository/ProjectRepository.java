package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByStdPRN(Long Id); 
	List<Project> findAllByStatus(String status);
	
}
