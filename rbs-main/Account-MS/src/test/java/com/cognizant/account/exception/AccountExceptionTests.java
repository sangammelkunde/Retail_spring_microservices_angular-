package com.cognizant.account.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.cognizant.account.model.AccountErrorResponse;

/*
 * The test class for exception tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountExceptionTests {

	/*
	 * Test for the AccountErroResponse entity.
	 */
	@Test
	public void testErrorResponse() {

		AccountErrorResponse errRes = new AccountErrorResponse(null, null, null, null);
		AccountErrorResponse err = new AccountErrorResponse();
		err.setMessage("Error.");
		err.setStatus(HttpStatus.BAD_REQUEST);
		err.setTimestamp(null);
		err.setReason("Err.");

		assertEquals("Error.", err.getMessage());
		assertEquals("Err.", err.getReason());
		assertEquals(HttpStatus.BAD_REQUEST, err.getStatus());
		assertEquals(null, err.getTimestamp());
	}

	/*
	 * Test for AccessDenied exception.
	 */
	@Test
	public void testAccessDenied() {
		AccessDeniedException ac = new AccessDeniedException();
		AccessDeniedException ac1 = new AccessDeniedException("Access Denied.");

		assertEquals("Access Denied.", ac1.getMessage());

	}

	/*
	 * Test for AccountNotFound exception.
	 */
	@Test
	public void testAccountException() {
		AccountNotFoundException ae = new AccountNotFoundException();
		AccountNotFoundException ae1 = new AccountNotFoundException("Account not found.");

		assertEquals("Account not found.", ae1.getMessage());
	}

	/*
	 * Test for MinimumBalance exception.
	 */
	@Test
	public void testMinimumBalance() {
		MinimumBalanceException mb = new MinimumBalanceException();
		MinimumBalanceException mb1 = new MinimumBalanceException("Minimum Balance Exception.");

		assertEquals("Minimum Balance Exception.", mb1.getMessage());
	}
}
