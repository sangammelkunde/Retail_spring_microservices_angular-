package com.cognizant.transactionservice.feign;

/**
 * 
 * This is Feign Client for Account Micro-service
 *
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.cognizant.transactionservice.models.Account;

@FeignClient(name = "account-ms", url = "${feign.url-account-ms}")
public interface AccountFeign {

	@GetMapping("/getAccount/{accountId}")
	public Account getAccount(@RequestHeader("Authorization") String token,
			@PathVariable(name = "accountId") long accountId);

	@PostMapping("/updateAccountById/{accountId}")
	public Account updateAccountById(@RequestHeader("Authorization") String token,
			@PathVariable(name = "accountId") long accountId, @RequestParam double newBalance);

}
