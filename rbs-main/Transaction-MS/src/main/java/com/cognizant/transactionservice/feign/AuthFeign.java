package com.cognizant.transactionservice.feign;

/**
 * 
 * This is Feign Client for Authentication Micro-service
 *
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.cognizant.transactionservice.models.AppUser;
import com.cognizant.transactionservice.models.AuthenticationResponse;

@FeignClient(name = "auth-ms", url = "${feign.url-auth-ms}")
public interface AuthFeign {

	@GetMapping("/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token);

	@PostMapping("/login")
	public ResponseEntity<AppUser> login(@RequestBody AppUser appUserloginCredentials);
}
