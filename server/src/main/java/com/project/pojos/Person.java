package com.project.pojos;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@MappedSuperclass
public class Person {
	
	@Column(length = 50, name = "first_name")
	@NotBlank(message = "First Name is required!!!!")
	private String firstName;
	@Column(length = 50, name = "last_name")
	@NotBlank(message = "Last Name is required!!!!")
	private String lastName;
	
	
	public Person(@NotBlank(message = "First Name is required!!!!") String firstName,
			@NotBlank(message = "Last Name is required!!!!") String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
