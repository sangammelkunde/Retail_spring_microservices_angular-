package com.cognizant.transactionservice.exception;

/**
 * 
 *         Access Denied Exception Class
 *
 */

public class AccessDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message) {
		super("Access Denied. You're Not Authorised");
	}

}
