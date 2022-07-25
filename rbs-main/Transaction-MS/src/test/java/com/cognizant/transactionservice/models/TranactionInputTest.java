package com.cognizant.transactionservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.transactionservice.util.TransactionInput;

public class TranactionInputTest {
	
	@Test
	public void testTransctionInput() {
		TransactionInput input = new TransactionInput();
		input.setSourceAccount(1L);
		input.setTargetAccount(2L);
		input.setAmount(1000);
		input.setTypeOfTrasaction("Transfer.");
		
		assertEquals(1L, input.getSourceAccount());
		assertEquals(2L, input.getTargetAccount());
		assertEquals(1000, input.getAmount());
		assertEquals("Transfer.", input.getTypeOfTrasaction());
	}

}
