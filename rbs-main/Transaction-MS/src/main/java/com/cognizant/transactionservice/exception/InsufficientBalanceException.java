package com.cognizant.transactionservice.exception;

/**
 * 
 *         Insufficient Balance Exception Class
 *
 */

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super();
	}

	public InsufficientBalanceException(String message) {
		super("Insufficient Balance to complete the transaction. Please Check your balance and try with less amount.");
	}

}
