package com.cognizant.rules.service;

import com.cognizant.rules.model.RulesStatus;

public interface RulesService {

	RulesStatus checkMinBal(double balance);

	double calculateServiceCharge(double balance);

}
