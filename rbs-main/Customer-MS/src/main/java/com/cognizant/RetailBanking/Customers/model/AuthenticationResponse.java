package com.cognizant.RetailBanking.Customers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {
	private String userid;
	private String name;
	private boolean isValid;
}