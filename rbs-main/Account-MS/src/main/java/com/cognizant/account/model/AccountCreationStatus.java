package com.cognizant.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class that is sent as a response for the create account request. 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccountCreationStatus {

	private long accountId;
	private String message;

	public AccountCreationStatus(String message) {
		super();
		this.message = message;
	}

}
