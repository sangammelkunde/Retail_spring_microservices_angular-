package com.cognizant.transactionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cognizant.transactionservice.models.RulesStatus;

/**
 * 
 *         This is Feign Client for Rules Micro-service
 *
 */

@FeignClient(name = "rules-ms", url = "${feign.url-rules-ms}")
public interface RulesFeign {

	@PostMapping("/evaluate")
	public RulesStatus evaluateMinBalance(@RequestParam double balance);

}
