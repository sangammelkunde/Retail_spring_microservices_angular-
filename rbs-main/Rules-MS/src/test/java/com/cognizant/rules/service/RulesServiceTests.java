package com.cognizant.rules.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.cognizant.rules.model.RulesStatus;

@RunWith(MockitoJUnitRunner.class)
public class RulesServiceTests {

	@InjectMocks
	private RulesServiceImpl rulesService;

	@Test
	public void checkMinBal() {

		RulesStatus rulesStatus = new RulesStatus();
		rulesStatus = rulesService.checkMinBal(400);
		assertEquals("DENIED", rulesStatus.getStatus());
		assertEquals("The account failed the minimum balance criteria", rulesStatus.getMessage());
		rulesStatus = rulesService.checkMinBal(1000);
		assertEquals("ALLOWED", rulesStatus.getStatus());
		assertEquals("The account passed the minimum balance criteria", rulesStatus.getMessage());
	}

	@Test
	public void calculateServiceCharge() {
		double case1 = rulesService.calculateServiceCharge(250);
		assertEquals(50, case1);
		double case2 = rulesService.calculateServiceCharge(120);
		assertEquals(70, case2);
		double case3 = rulesService.calculateServiceCharge(375);
		assertEquals(40, case3);
		double case4 = rulesService.calculateServiceCharge(125);
		assertEquals(60, case4);
	}
}
