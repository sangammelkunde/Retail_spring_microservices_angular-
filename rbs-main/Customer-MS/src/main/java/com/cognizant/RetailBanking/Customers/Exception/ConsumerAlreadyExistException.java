package com.cognizant.RetailBanking.Customers.Exception;
public class ConsumerAlreadyExistException extends RuntimeException {

	/**
	 * 		Consumer Already Exist Exception
	 */
	private static final long serialVersionUID = -2862505141325062716L;

	public ConsumerAlreadyExistException() {
		super();
	}

	public ConsumerAlreadyExistException(String message) {
		super("This Customer is already registered. Please Login");
	}

}
