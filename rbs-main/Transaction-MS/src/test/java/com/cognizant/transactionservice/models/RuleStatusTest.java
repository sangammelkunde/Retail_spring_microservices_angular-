package com.cognizant.transactionservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class RuleStatusTest {

	RulesStatus rs=new RulesStatus();
	RulesStatus rs2=new RulesStatus("up","hi");
	
	@Test
	void setStatusTest() {
		rs.setStatus("up");
		assertEquals("up", rs.getStatus());
	}

	
	
	@Test
	void setMessageTest() {
		rs.setMessage("Not Valid");
		assertEquals("Not Valid", rs.getMessage());
	}
	
}




