package com.cognizant.rules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.rules.model.RulesStatus;
import com.cognizant.rules.service.RulesServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RulesController {

	@Autowired
	RulesServiceImpl rulesService;

	/*
	 * method to evaluate minimum balance | Input : balance | Output : Rules Status
	 */
	@PostMapping("/evaluate")
	public RulesStatus evaluateMinBalance(@RequestParam double balance) {
		log.info("START : Evaluate Min Balance");
		log.info("END : Evaluate Min Balance");
		return rulesService.checkMinBal(balance);

	}

	/*
	 * Method to get Service Charge Details for the account | Input : balance |
	 * Output : Service Charge
	 */
	@GetMapping("/serviceCharge")
	public double getServiceCharge(@RequestParam double balance) {
		log.info("START : Get Service Charge");
		log.info("END : Get Service Charge");
		return rulesService.calculateServiceCharge(balance);

	}
}
