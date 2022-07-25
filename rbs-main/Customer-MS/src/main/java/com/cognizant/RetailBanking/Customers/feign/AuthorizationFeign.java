package com.cognizant.RetailBanking.Customers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.RetailBanking.Customers.model.AppUser;
import com.cognizant.RetailBanking.Customers.model.AuthenticationResponse;


@FeignClient(name="auth-ms",url="${feign.url-auth-ms}")
public interface AuthorizationFeign {

	
	
	
	// Create Customer
	/**
	 * 
	 * @param appUserCredentials
	 * @return
	 */
	@PostMapping(value="/createUser")
	public ResponseEntity<?> createUser(@RequestBody AppUser appUserCredentials );
	
	

	/**
	 * @Create login
	 * @param appUserloginCredentials
	 * @return
	 */
	@PostMapping(value="/login")
	public ResponseEntity<AppUser> login(@RequestBody AppUser appUserloginCredentials);
	
	
	/**
	 * @VALIDATE TOKEN
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token);

	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	@GetMapping("/role/{userid}")
	public String getRole(@PathVariable("userid") String userid);

}
