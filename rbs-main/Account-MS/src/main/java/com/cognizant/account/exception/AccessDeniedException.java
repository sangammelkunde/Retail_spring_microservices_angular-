package com.cognizant.account.exception;

/*
 * Exception class for handling access denied exception.
 * Mainly occurs due to invalid token.
 */

public class AccessDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message) {
		super("Access Denied. You're not authorised to access");
	}

}
