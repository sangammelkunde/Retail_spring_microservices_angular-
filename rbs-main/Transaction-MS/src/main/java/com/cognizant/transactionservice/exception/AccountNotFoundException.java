package com.cognizant.transactionservice.exception;

/**
 * 
 *         Account Not Found Exception Class Implementation
 *
 */

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String message) {
		super("The Account does not exist. Please get your Account Created.");
	}

}
