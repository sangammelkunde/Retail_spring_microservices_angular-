package com.cognizant.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.authentication.model.AuthenticationResponse;
import com.cognizant.authentication.repository.UserRepository;

/**
 * Token validation service class
 *
 */
@Component
public class Validationservice {

	/**
	 * Autowired JwtUtil class
	 */
	@Autowired
	private JwtUtil jwtutil;

	/**
	 * Autowired UserRepository class
	 */
	@Autowired
	private UserRepository userRepo;

	/**
	 * Method to Authenticate User
	 * 
	 * @param token
	 * @return if token is valid then true else false
	 */
	public AuthenticationResponse validate(String token) {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		String jwt = token.substring(7);

		if (jwtutil.validateToken(jwt)) {
			authenticationResponse.setUserid(jwtutil.extractUsername(jwt));
			authenticationResponse.setValid(true);
			authenticationResponse.setName(userRepo.findById(jwtutil.extractUsername(jwt)).get().getUsername());
		} else {
			authenticationResponse.setValid(false);
		}
		return authenticationResponse;
	}
}