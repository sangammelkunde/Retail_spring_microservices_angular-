package com.cognizant.CustomerServiceTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognizant.RetailBanking.Customers.model.Account;



 class AccountTest {

	Account account = new Account();
	Account account2 = new Account(111, "111", "pawan", "savings", 100.0);

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
	void setBalanceTest() {
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
		assertEquals(1, account.getAccountId());
	}

	@Test
	void getCustomerTest() {
		account.setCustomerId("Cust101");
		assertEquals("Cust101", account.getCustomerId());
	}

	@Test
	void getAcctypeTest() {
		account.setAccountType("Savings");
		assertEquals("Savings", account.getAccountType());
	}

	@Test
	void getTokenTest() {
		account.setBalance(5000);
		assertEquals(5000, account.getBalance());
	}
	
	@Test
	void getOwnerTest() {
		account.setOwnerName("Nagarjun");
		assertEquals("Nagarjun", account.getOwnerName());
	}

}
