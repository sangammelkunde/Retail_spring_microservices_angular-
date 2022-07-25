package com.cognizant.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class used to format error response of authentication microservice. 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

	private String userid;
	private String name;
	private boolean isValid;
}