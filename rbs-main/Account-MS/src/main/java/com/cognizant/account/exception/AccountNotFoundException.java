package com.cognizant.account.exception;

/*
 * Exception class for handling account not found exception.
 * Mainly occurs due to invalid accountId.
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
