package com.project.dto;

import com.project.pojos.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpAdminResponse {

	private Admin adm;
	private String mesg;
}
