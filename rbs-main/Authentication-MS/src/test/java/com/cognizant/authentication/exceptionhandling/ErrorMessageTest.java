package com.cognizant.authentication.exceptionhandling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.cognizant.authentication.errorhandling.ErrorMessage;

/**
 * Error Message Class Test Cases
 * 
 * @author Pod-4
 *
 */
public class ErrorMessageTest {
	/**
	 * Error message Class's Object
	 */
	ErrorMessage obj = new ErrorMessage();

	/**
	 * Local Date Time class's object to get current time
	 */
	LocalDateTime now = LocalDateTime.now();

	/**
	 * Error Message Constructor Test Case
	 */
	@Test
	void ErrorMessageConstrucutorTest() {

		ErrorMessage Obj = new ErrorMessage(HttpStatus.NOT_FOUND, now, "Demo Error Message");
		assertTrue(Obj.getStatus() == HttpStatus.NOT_FOUND);
		assertTrue(Obj.getTimestamp() == now);
		assertTrue(Obj.getMessage() == "Demo Error Message");
	}

	/**
	 * Set Date Test Case
	 */
	@Test
	void setDateTest() {
		obj.setTimestamp(now);
		assertEquals(now, obj.getTimestamp());
	}

	/**
	 * Set Message Test Case
	 */
	@Test
	void setMessageTest() {
		obj.setMessage("Demo Error Message");
		assertEquals("Demo Error Message", obj.getMessage());
	}

	/**
	 * Set HttpStatus Test Case
	 */
	@Test
	void setHttpStatusTest() {
		obj.setStatus(HttpStatus.NOT_FOUND);
		assertEquals(HttpStatus.NOT_FOUND, obj.getStatus());
	}
}
