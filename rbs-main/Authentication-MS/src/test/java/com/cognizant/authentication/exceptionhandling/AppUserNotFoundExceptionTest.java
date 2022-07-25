package com.cognizant.authentication.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * AppUserNotFound Exception Test cases
 * 
 * @author Pod-4
 *
 */
public class AppUserNotFoundExceptionTest {
	/**
	 * AppUserNotFound Get Message Test Case
	 */
	@Test
	void GetMessageTest() {
		AppUserNotFoundException obj = new AppUserNotFoundException();
		assertEquals(null, obj.getMessage());
	}
}
