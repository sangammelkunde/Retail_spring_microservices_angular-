package com.cognizant.CustomerServiceTest.model;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import com.cognizant.RetailBanking.Customers.model.CustomErrorResponse;

public class CustomErrorResponseTest 
{

	CustomErrorResponse CusObj=new CustomErrorResponse();
	LocalDateTime now = LocalDateTime.now();
	
	@Test
	void ConstructorTest()
	{
		CustomErrorResponse Obj=new CustomErrorResponse(now,HttpStatus.NOT_FOUND,"Invalid User","Demo Error Message");
		assertTrue(Obj.getMessage()=="Demo Error Message");
		assertTrue(Obj.getReason()=="Invalid User");
		assertTrue(Obj.getStatus()==HttpStatus.NOT_FOUND);
		assertTrue(Obj.getTimestamp()==now);
	}
	
	@Test
	void getStatusTest()
	{
		CusObj.setStatus(HttpStatus.NOT_FOUND);
		assertTrue(CusObj.getStatus()==HttpStatus.NOT_FOUND);
	}
	
	@Test
	void setStatusTest()
	{
		CusObj.setStatus(HttpStatus.NOT_FOUND);
		assertTrue(CusObj.getStatus()==HttpStatus.NOT_FOUND);
	}
	
	@Test
	void ConstructorNegativeTest()
	{
		CustomErrorResponse Obj=new CustomErrorResponse();
		assertFalse(Obj.getMessage()=="Demo Error Message");
		assertFalse(Obj.getReason()=="Invalid User");
		assertFalse(Obj.getStatus()==HttpStatus.NOT_FOUND);
		assertFalse(Obj.getTimestamp()==now);
	}
	
	@Test
	void getTimestampTest()
	{
		CusObj.setTimestamp(now);
		assertTrue(CusObj.getTimestamp()==now);
	}
	
	@Test
	void setTimestampTest()
	{
		CusObj.setTimestamp(now);
		assertTrue(CusObj.getTimestamp()==now);
	}
	
	@Test
	void getReasonTest()
	{
		CusObj.setReason("Invalid User");
		assertTrue(CusObj.getReason()=="Invalid User");
	}
	
	@Test
	void setReasonTest()
	{
		CusObj.setReason("Invalid User");
		assertTrue(CusObj.getReason()=="Invalid User");
	}
	
	@Test
	void getMessageTest()
	{
		CusObj.setMessage("Demo Error Message");
		assertEquals(CusObj.getMessage(),"Demo Error Message");
	}
	
	@Test
	void setMessageTest()
	{
		CusObj.setMessage("Demo Error Message");
		assertEquals(CusObj.getMessage(),"Demo Error Message");
	}
	
	
	
}
