package com.cognizant.transactionservice.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;



class AccountTest {

	Transaction t = new Transaction();
	List<Transaction> list = new ArrayList<Transaction>();
	Account account = new Account();
	Account account1 = new Account(1, "abc", 10, "user", "nagarjun");

	@Test
	void setAccountIdTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

	@Test
	void setCustomerIdTest() {
		account.setCustomerId("Cust101");
		assertEquals("Cust101", account.getCustomerId());
	}

	@Test
	void setCurrentBalanceTest() {
		account.setBalance(5000);
		assertEquals(5000, account.getBalance());
	}

	@Test
	void setAccountTypeTest() {
		account.setAccountType("Savings");
		assertEquals("Savings", account.getAccountType());
	}

	@Test
	void setOwnerNameTest() {
		account.setOwnerName("Nagarjun");
		assertEquals("Nagarjun", account.getOwnerName());
	}

	

	@Test
	void getAccTest() {
		account.setAccountId(1);
		assertTrue(account.getAccountId() == 1);
	}

	@Test
	void getCustomerTest() {
		account.setCustomerId("1");
		assertTrue(account.getCustomerId() == "1");
	}

	@Test
	void getAcctypeTest() {
		account.setAccountType("abc");
		assertTrue(account.getAccountType() == "abc");
	}

	@Test
	void getTokenTest() {
		account.setBalance(10);
		assertTrue(account.getBalance() == 10);
	}

	@Test
	void getOwnerTest() {
		account.setOwnerName("nagarjun");
		assertTrue(account.getOwnerName() == "nagarjun");
	}
}
