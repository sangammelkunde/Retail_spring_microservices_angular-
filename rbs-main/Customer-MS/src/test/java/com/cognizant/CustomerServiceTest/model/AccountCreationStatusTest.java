package com.cognizant.CustomerServiceTest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognizant.RetailBanking.Customers.model.AccountCreationStatus;

public class AccountCreationStatusTest 
{
	AccountCreationStatus AccObj=new AccountCreationStatus();
	
	@Test
	void setaccountIdTest()
	{
		AccObj.setAccountId(111);
		assertEquals(111,AccObj.getAccountId());
	}
	
	@Test
	void getaccountIdTest()
	{
		AccObj.setAccountId(111);
		assertTrue(AccObj.getAccountId()==111);
	}
	
	@Test
	void setMessageTest()
	{
		AccObj.setMessage("Demo Message");
		assertEquals("Demo Message", AccObj.getMessage());
	}
	
	@Test
	void setMessageNegativeTest()
	{
		AccObj.setMessage("Demo Message");
		assertTrue(AccObj.getMessage()=="Demo Message");
	}
	
	@Test
	void constructorTest()
	{
		AccountCreationStatus Obj=new AccountCreationStatus(101,"Account Created Successfull");
		assertEquals(101,Obj.getAccountId());
		assertEquals("Account Created Successfull",Obj.getMessage());
	}
	
	@Test
	void constructorNegativeTest()
	{
		AccountCreationStatus Obj=new AccountCreationStatus();
		assertFalse(Obj.getAccountId()==101);
		assertFalse(Obj.getMessage()=="Account Created Successfull");
	}

}
