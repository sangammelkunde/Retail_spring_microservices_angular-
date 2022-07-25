package com.cognizant.RetailBanking.Customers.Exception;
public class CustomerNotFoundException extends RuntimeException {

	/**
	 *    Customer Not Found Exception
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message) {
		super("Customer Does not Exists. Please get registered and then try again.");
	}

}
