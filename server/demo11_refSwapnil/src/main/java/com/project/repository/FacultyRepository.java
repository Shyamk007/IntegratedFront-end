package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	Optional<Faculty> findByFacultyCredentialsUserName(String userName);

}
