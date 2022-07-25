package com.cognizant.transactionservice.models;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TransactionStatusTest {
	
	TransactionStatus ts= new TransactionStatus();
	TransactionStatus ts1=new TransactionStatus("ok",100,100);
	
	

	@Test
	void setInitialBalanceTest() {
		ts.setInitialBalance(1000);
		assertEquals(1000, ts.getInitialBalance());
	}
	
	@Test
	void setFinalBalanceTest() {
		ts.setFinalBalance(1000);
		assertEquals(1000,ts.getFinalBalance());
	}
	
	@Test
	void setMessageTest() {
		ts.setMessage("OK");
		assertEquals("OK",ts.getMessage());
	}
	
}
