package com.cognizant.transactionservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.transactionservice.util.TransactionInput;

public class TransactionInputTest {
	
	
	@Test
	public void testTransactionInput() {
		TransactionInput tranInput = new TransactionInput();
		tranInput.setSourceAccount(1L);
		tranInput.setTargetAccount(2L);
	
		tranInput.setTypeOfTrasaction("Transfer.");
		
		assertEquals(1L, tranInput.getSourceAccount());
		assertEquals(2L, tranInput.getTargetAccount());
		
		assertEquals("Transfer.", tranInput.getTypeOfTrasaction());
		
	}

}
