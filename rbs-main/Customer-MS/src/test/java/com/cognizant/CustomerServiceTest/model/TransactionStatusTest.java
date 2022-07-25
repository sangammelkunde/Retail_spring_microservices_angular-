package com.cognizant.CustomerServiceTest.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognizant.RetailBanking.Customers.model.TransactionStatus;

public class TransactionStatusTest 
{
	TransactionStatus TransStatusObj=new TransactionStatus();
	
	@Test
	void ConstructorTest()
	{
		TransactionStatus Obj=new TransactionStatus("Demo Message", 10.0,101.0);
		assertTrue(Obj.getFinalBalance()==101.0);
		assertTrue(Obj.getInitialBalance()==10.0);
		assertTrue(Obj.getFinalBalance()==101.0);
	}
	
	@Test
	void ConstructorNegativeTest()
	{
		TransactionStatus Obj=new TransactionStatus();
		assertFalse(Obj.getFinalBalance()==101.0);
		assertFalse(Obj.getInitialBalance()==10.0);
		assertFalse(Obj.getFinalBalance()==101.0);
	}
	
	@Test
	void getInitialBalanceTest()
	{
		TransStatusObj.setInitialBalance(10.0);
		assertTrue(TransStatusObj.getInitialBalance()==10.0);
	}
	
	@Test
	void setInitialBalanceTest()
	{
		TransStatusObj.setInitialBalance(10.0);
		assertTrue(TransStatusObj.getInitialBalance()==10.0);
	}
	
	@Test
	void getFinalBalanceTest()
	{
		TransStatusObj.setFinalBalance(101.0);
		assertTrue(TransStatusObj.getFinalBalance()==101.0);
	}
	
	@Test
	void setFinalBalanceTest()
	{
		TransStatusObj.setFinalBalance(101.0);
		assertTrue(TransStatusObj.getFinalBalance()==101.0);
	}
	
}
