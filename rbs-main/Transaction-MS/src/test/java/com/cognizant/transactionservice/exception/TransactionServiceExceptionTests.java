package com.cognizant.transactionservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.http.HttpStatus;


public class TransactionServiceExceptionTests {
	

	
	/**
	 * Test for access denied
	 */
	@Test
	public void testAccessDenied() {
		AccessDeniedException ac = new AccessDeniedException();
		AccessDeniedException ac1 = new AccessDeniedException("Access Denied.");
		
		assertEquals("Access Denied.", ac1.getMessage());
		
	}
	
	@Test
	public void testAccountException() {
		AccountNotFoundException ae = new AccountNotFoundException();
		AccountNotFoundException ae1 = new AccountNotFoundException("Account not found.");
		
		assertEquals("Account not found.", ae1.getMessage());
	}
	
	@Test
	public void testMinimumBalance() {
		MinimumBalanceException mb = new MinimumBalanceException();
		MinimumBalanceException mb1 = new MinimumBalanceException("Minimum Balance Exception.");
		
		assertEquals("Minimum Balance Exception.", mb1.getMessage());
	}

}
