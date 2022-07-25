package com.cognizant.rules.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cognizant.rules.model.RulesStatus;
import com.cognizant.rules.service.RulesServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RulesControllerTests {

	// mocking rules service Impl
	@Mock
	RulesServiceImpl rulesService;

	// mocking rules Controller
	@InjectMocks
	RulesController rulesController;

	@Test
	public void evaluateMinBalance() {
		// Test case Allowed
		RulesStatus rulesStatus1 = new RulesStatus("ALLOWED", "The account passed the minimum balance criteria");
		// Test case Denied
		when(rulesService.checkMinBal(600)).thenReturn(rulesStatus1);
		RulesStatus rulesStatus2 = new RulesStatus("DENIED", "The account failed the minimum balance criteria");
		when(rulesService.checkMinBal(400)).thenReturn(rulesStatus2);
		// Checking Expected and Actual result
		assertEquals("ALLOWED", rulesController.evaluateMinBalance(600).getStatus());
		assertEquals("DENIED", rulesController.evaluateMinBalance(400).getStatus());
		assertEquals("The account passed the minimum balance criteria",
				rulesController.evaluateMinBalance(600).getMessage());
		assertEquals("The account failed the minimum balance criteria",
				rulesController.evaluateMinBalance(400).getMessage());

	}

	@Test
	public void getServiceCharge() {
		// check for 50 percent balance
		when(rulesService.calculateServiceCharge(250)).thenReturn(50.0);
		// check for below 25 percent balance
		when(rulesService.calculateServiceCharge(120)).thenReturn(70.0);
		// check for 75 percent balance
		when(rulesService.calculateServiceCharge(375)).thenReturn(40.0);
		// check for 25 percent balance
		when(rulesService.calculateServiceCharge(125)).thenReturn(60.0);
		// Checking Expected and Actual result
		assertEquals(40, rulesController.getServiceCharge(750));
		assertEquals(50, rulesController.getServiceCharge(500));
		assertEquals(60, rulesController.getServiceCharge(250));
		assertEquals(70, rulesController.getServiceCharge(240));

	}
}
