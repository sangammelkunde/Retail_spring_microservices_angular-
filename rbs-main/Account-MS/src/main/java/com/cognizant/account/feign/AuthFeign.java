package com.cognizant.account.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.account.model.AuthenticationResponse;

/*
 * Feign Client for the authentication microservice.
 * This is used to validate the token for each request made to the account microservice.
 */

@FeignClient(name = "auth-ms", url = "${feign.url.auth-ms}")
public interface AuthFeign {

	@GetMapping("/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token);

}
