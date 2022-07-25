package com.cognizant.transactionservice.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransactionTest {

	Transaction transaction = new Transaction();
	Transaction transaction2 = new Transaction(1l, 1l, "Amit B", 3l, "Pratik B", 1000, null, "deposit");

	@Test
	void setIdTest() {
		transaction.setId(1);
		assertEquals(1, transaction.getId());
	}

	@Test
	void setSourceAccountIdTest() {
		transaction.setSourceAccountId(1);
		assertEquals(1, transaction.getSourceAccountId());
	}

	@Test
	void setTargetOwnerNameTest() {
		transaction.setTargetOwnerName("Amit B");
		assertEquals("Amit B", transaction.getTargetOwnerName());
	}
	
	@Test
	void setSourceOwnerNameTest() {
		transaction.setSourceOwnerName("Amit B");
		assertEquals("Amit B", transaction.getSourceOwnerName());
	}

	@Test
	void setTargetAccountIdTest() {
		transaction.setTargetAccountId(1);
		;
		assertEquals(1, transaction.getTargetAccountId());
	}

	@Test
	void setAmountTest() {
		transaction.setAmount(1000);
		assertEquals(1000, transaction.getAmount());
	}

	@Test
	void setReferenceTest() {
		transaction.setTypeOfTransaction("Deposit");
		assertEquals("Deposit", transaction.getTypeOfTransaction());
	}

	@Test
	void setInitiationDateTest() {
		transaction.setDateOfTransaction(null);
		assertEquals(null, transaction.getDateOfTransaction());
	}

}
