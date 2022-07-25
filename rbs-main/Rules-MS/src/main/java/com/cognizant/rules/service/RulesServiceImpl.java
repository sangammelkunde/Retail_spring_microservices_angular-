package com.cognizant.rules.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cognizant.rules.model.RulesStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RulesServiceImpl implements RulesService {

	// Default Minimum Balance Value set by the bank
	private final float MIN_BAL = 500;

	@Value("${rule.allowed}")
	String allowedMsg;

	@Value("${rule.denied}")
	String deniedMsg;

	/*
	 * Method to check the Minimum balance criteria | Input : balance | Output :
	 * Rules Status
	 */

	@Override
	public RulesStatus checkMinBal(double balance) {
		log.info("START : Check MinBal");
		RulesStatus rulesStatus = new RulesStatus();
		if (balance < MIN_BAL) {
			rulesStatus.setStatus("DENIED");
			rulesStatus.setMessage(deniedMsg);
		} else {
			rulesStatus.setStatus("ALLOWED");
			rulesStatus.setMessage(allowedMsg);
		}
		log.info("END : Check MinBal");
		return rulesStatus;

	}

	/*
	 * Method to check the Minimum balance criteria | Input : balance | Output :
	 * Service Charge
	 */
	@Override
	public double calculateServiceCharge(double balance) {
		log.info("START : Calculate Service Charge");
		double serviceCharge = 0;

		float fiftyPercentValue = (float) 0.5 * MIN_BAL;
		float twentyfivePercentValue = (float) 0.25 * MIN_BAL;
		float seventyfivePercentValue = (float) 0.75 * MIN_BAL;

		float gstValue = 10;
		// Service charge calculation based on different ratios
		if (balance >= seventyfivePercentValue)
			serviceCharge = 30 + gstValue;
		else if (balance >= fiftyPercentValue && balance < seventyfivePercentValue)
			serviceCharge = 40 + gstValue;
		else if (balance >= twentyfivePercentValue && balance < fiftyPercentValue)
			serviceCharge = 50 + gstValue;
		else
			serviceCharge = 60 + gstValue;

		log.info("END : Calculate Service Charge");
		return serviceCharge;
	}

}
