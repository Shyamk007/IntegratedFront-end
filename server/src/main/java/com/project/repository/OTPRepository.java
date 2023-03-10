package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pojos.OTP;

public interface OTPRepository extends JpaRepository<OTP, String> {

}
