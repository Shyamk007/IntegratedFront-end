package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByStudentCredentialsUserName(String userName);
}
