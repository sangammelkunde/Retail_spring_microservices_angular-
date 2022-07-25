package com.cognizant.transactionservice.exception;

/**
 * 
 *         Minimum Balance Exception Class
 *
 */

public class MinimumBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MinimumBalanceException() {
		super();
	}

	public MinimumBalanceException(String message) {
		super("You have reached your minimum balance.");
	}

}
