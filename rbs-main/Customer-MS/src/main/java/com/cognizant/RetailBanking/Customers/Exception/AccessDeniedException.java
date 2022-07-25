package com.cognizant.RetailBanking.Customers.Exception;

public class AccessDeniedException extends RuntimeException{

	/**
	 * 		Access Denied Exception
	 */
	private static final long serialVersionUID = 895616911464801474L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message) {
		super("Access Denied. You're Not Authorised");
	}

	
	
}
