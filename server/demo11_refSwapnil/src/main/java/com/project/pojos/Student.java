package com.project.pojos;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Students")
@ToString(callSuper = true)
public class Student extends Person {

	@Id
	@Column(name = "Student_prn")
	private Long PRN;
	@Column(name = "Roll_Number")
	private Long RollNo;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "Submitted_Date")
	private LocalDate SubmitDate;
	@Column(name = "Student_batch")
	@Enumerated(value = EnumType.STRING)
	private Batch batch;

	@OneToOne(mappedBy = "std", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Project prj;
	
	public void setStudentProjectDetails(Project projectDetails) {
		prj = projectDetails;
		projectDetails.setStudentProjectDetails(this);
	}
	
	

	@OneToOne
	@JoinColumn(name = "user_name")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Credentials studentCredentials;
	
	public Student(@NotBlank(message = "First Name is required!!!!") String firstName,
			@NotBlank(message = "Last Name is required!!!!") String lastName, Long pRN, Long rollNo, String email,
			LocalDate submitDate, Batch batch) {
		super(firstName, lastName);
		PRN = pRN;
		RollNo = rollNo;
		this.email = email;
		SubmitDate = submitDate;
		this.batch = batch;
	}

	public Long getPRN() {
		return PRN;
	}

	public void setPRN(Long pRN) {
		PRN = pRN;
	}

	public Long getRollNo() {
		return RollNo;
	}

	public void setRollNo(Long rollNo) {
		RollNo = rollNo;
	}

	public LocalDate getSubmitDate() {
		return SubmitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		SubmitDate = submitDate;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Credentials getStudentCredentials() {
		return studentCredentials;
	}

	public void setStudentCredentials(Credentials studentCredentials) {
		this.studentCredentials = studentCredentials;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
