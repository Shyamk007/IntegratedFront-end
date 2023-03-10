package com.project.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name="Faculty")
@ToString(callSuper = true)

public class Faculty extends Person {
	
	@Id
	@Column(name="faculty_Id")
	@SequenceGenerator(name="Faculty_id_generator",sequenceName = "faculty_id_Sequence",initialValue = 41222000,allocationSize = 1)
	@GeneratedValue(generator = "Faculty_id_generator")
	private Long FacultyId;

	@Column(name="email",length=50)
	private String email;
	
	@Column(name="qualification")
	private String qualification;

	@Column(name="Experience")
	private Integer experience;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Account_Status")
	private Account_Status accStatus;
	
	@OneToOne
	@JoinColumn(name="user_name")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Credentials facultyCredentials;
	
	@OneToMany(mappedBy = "faculty",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Project> projects = new ArrayList<Project>();
	
	
	
	public Faculty(@NotBlank(message = "First Name is required!!!!") String firstName,
			@NotBlank(message = "Last Name is required!!!!") String lastName, Long facultyId, String email,
			String qualification, Integer experience) {
		super(firstName, lastName);
		FacultyId = facultyId;
		this.email = email;
		this.qualification = qualification;
		this.experience = experience;
		this.accStatus=Account_Status.NOT_VERIFIED;
	}
	

	public Long getFacultyId() {
		return FacultyId;
	}

	public void setFacultyId(Long facultyId) {
		FacultyId = facultyId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Credentials getFacultyCredentials() {
		return facultyCredentials;
	}

	public void setFacultyCredentials(Credentials facultyCredentials) {
		this.facultyCredentials = facultyCredentials;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public List<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}


	public Account_Status getAccStatus() {
		return accStatus;
	}


	public void setAccStatus(String accStatus) {
		this.accStatus = Account_Status.valueOf(accStatus);
	}

	
	
	
}
