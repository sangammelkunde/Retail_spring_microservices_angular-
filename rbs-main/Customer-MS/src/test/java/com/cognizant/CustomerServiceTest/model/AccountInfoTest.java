package com.cognizant.CustomerServiceTest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognizant.RetailBanking.Customers.model.AccountInfo;

public class AccountInfoTest 
{
	AccountInfo Obj=new AccountInfo();
	
	@Test
	void ConstructorTest()
	{
		AccountInfo AccObj=new AccountInfo(111,89000.00);
		assertEquals(111,AccObj.getAccountId());
		assertTrue(AccObj.getBalance()==89000.0);
	}
	
	@Test
	void ConstructorNegativeTest()
	{
		AccountInfo AccObj=new AccountInfo();
		assertFalse(AccObj.getAccountId()==111);
		assertFalse(AccObj.getBalance()==89000.0);
	}
	
	@Test
	void setAccountIdTest()
	{
		Obj.setAccountId(111);
		assertTrue(Obj.getAccountId()==111);
	}
	
	
	@Test
	void setAccountIdNegativeTest()
	{
		Obj.setAccountId(111);
		assertEquals(111,Obj.getAccountId());
	}
	
	@Test
	void getBalanceTest()
	{
		Obj.setBalance(100.0);
		assertTrue(Obj.getBalance()==100.0);
	}
	
	@Test
	void getBalanceNegativeTest()
	{
		Obj.setBalance(10);
		assertFalse(Obj.getBalance()==100.0);
	}
	
}
