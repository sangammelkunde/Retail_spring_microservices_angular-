package com.cognizant.authentication.exceptionhandling;

/**
 * Class for handling APPUSER is not found exception
 * 
 */
public class AppUserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AppUserNotFoundException() {
		super();
	}

	public AppUserNotFoundException(final String message) {
		/**
		 * Constructor for AppUserNotFoundException
		 */
		super("User Does Not Exists. Create the User and Try Again.");
	}
}