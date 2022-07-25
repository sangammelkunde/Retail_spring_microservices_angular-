package com.cognizant.transactionservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.transactionservice.util.AccountInfo;

public class AccountInfoTest {
	
	
	@Test
	public void testAccountInfo() {
		AccountInfo acc = new AccountInfo();
		acc.setAccountId(1L);
		acc.setBalance(1000);
		
		assertEquals(1L, acc.getAccountId());
		assertEquals(1000, acc.getBalance());
	}
}
