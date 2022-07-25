package com.cognizant.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthenticationResponse pojo class with getter, setter method and constructor
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	private String userid;
	private String name;
	private boolean isValid;
}