package com.cognizant.RetailBanking.Customers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "rules-ms", url="${feign.url-rules-ms}")
public interface RulesFeign {

	
	/**
	 * 
	 * @param balance
	 * @return
	 */

	@GetMapping("/serviceCharge")
	public double getServiceCharge(@RequestParam double balance);
	
}
