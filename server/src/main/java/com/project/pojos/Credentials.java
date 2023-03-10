package com.project.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="User_credentials")
@ToString(exclude = "userRole")
@NoArgsConstructor
public class Credentials {

	@Id
	@NotBlank(message="userName Should be Provided !!")
	private String userName;
	
	@Column(name = "password")
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Password should be provided")
	private String password;
	
	@Column(name = "user_role", length = 50)
	@Enumerated(value = EnumType.STRING)
	private Role userRole;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(mappedBy = "studentCredentials",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private Student studPRN;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(mappedBy = "facultyCredentials",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private Faculty facId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(mappedBy = "adminCredentials",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private Admin adminId;
	
	
	public void setStudentPRN(Student stud) {
		studPRN = stud;
		stud.setStudentCredentials(this);
	}
	
	public void setFacultyId(Faculty fac) {
		facId = fac;
		fac.setFacultyCredentials(this);
	}
	
	public void setAdminId(Admin adm) {
		adminId = adm;
		adm.setAdminCredentials(this);
	}
	
	
	public Credentials(@NotBlank(message = "userName Should be Provided !!") String userName,
			@NotBlank(message = "Password should be provided") String password, Role userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = Role.valueOf(userRole);
	}

	public Student getStudPRN() {
		return studPRN;
	}

	public void setStudPRN(Student studPRN) {
		this.studPRN = studPRN;
	}

	public Faculty getFacId() {
		return facId;
	}

	public void setFacId(Faculty facId) {
		this.facId = facId;
	}

	public Admin getAdminId() {
		return adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	
	
}
