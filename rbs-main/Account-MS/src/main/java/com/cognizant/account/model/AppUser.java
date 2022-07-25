package com.cognizant.account.model;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The model class of the authentication microservice.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

	@NotNull
	private String userid;

	private String username;

	private String password;

	private String authToken;

	private String role;
}