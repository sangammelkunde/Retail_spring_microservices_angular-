package com.cognizant.transactionservice.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class TransactionErrorResponseTest {

	TransactionErrorResponse response= new TransactionErrorResponse();
	TransactionErrorResponse response2= new TransactionErrorResponse(null, HttpStatus.OK, "Not Valid", "Not Created");

	@Test
	void setStatusTest() {
		response.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, response.getStatus());
	}

	@Test
	void setReasonTest() {
		response.setReason("Not Valid");
		assertEquals("Not Valid", response.getReason());
	}

	@Test
	void setMessageTest() {
		response.setMessage("Not Valid");
		assertEquals("Not Valid", response.getMessage());
	}

	@Test
	void setTimeStampTest() {
		response.setTimestamp(null);
		assertEquals(null, response.getTimestamp());
	}

}
