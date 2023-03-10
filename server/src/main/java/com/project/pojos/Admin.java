package com.project.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Entity
@Table(name="Admin")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Admin extends Person{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Admin_Id;
	
	@Column(name="email",length=50)
	private String email;
	
	@OneToOne
	@JoinColumn(name="user_name")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Credentials adminCredentials;
	

	
	public Admin(@NotBlank(message = "First Name is required!!!!") String firstName,
			@NotBlank(message = "Last Name is required!!!!") String lastName, String email) {
		super(firstName, lastName);
		this.email = email;
	}
	
	
	
	public Long getAdmin_Id() {
		return Admin_Id;
	}

	public void setAdmin_Id(Long admin_Id) {
		Admin_Id = admin_Id;
	}


	public Credentials getAdminCredentials() {
		return adminCredentials;
	}


	public void setAdminCredentials(Credentials adminCredentials) {
		this.adminCredentials = adminCredentials;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}	
	
}
