package com.cognizant.account.exception;

/*
 * Exception class for handling minimum balance exception.
 * Mainly occurs due to violation of minimum balance rule.
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
