package com.cognizant.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cognizant.authentication.exceptionhandling.AppUserNotFoundException;
import com.cognizant.authentication.model.AppUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Login Service class to check the the login credentials with database
 * 
 *
 */

@Component
@Slf4j
public class LoginService {
	/**
	 * Autowired jwtutil class
	 */
	@Autowired
	private JwtUtil jwtutil;

	String NotValid="${CustomerDetailsService.NotValid}";
	
	/**
	 * Autowired BCryptPassword class
	 */
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Autowired CustomerDetailsService class
	 */
	@Autowired
	private CustomerDetailsService customerDetailservice;

	/**
	 * Login Method for check User Details from H2 database
	 * 
	 * @param appuser
	 * @return if user credentials are correct then AppUser object else
	 *         AppUserNotFound Exception
	 */
	public AppUser userLogin(AppUser appuser) throws AppUserNotFoundException {
		final UserDetails userdetails = customerDetailservice.loadUserByUsername(appuser.getUserid());
		String userid = "";
		String role = "";
		String token = "";

		log.info("Password From DB-->{}", userdetails.getPassword());
		log.info("Password From Request-->{}", encoder.encode(appuser.getPassword()));

		if (userdetails.getPassword().equals(appuser.getPassword())) {
			userid = appuser.getUserid();
			token = jwtutil.generateToken(userdetails);
			role = appuser.getRole();
			return new AppUser(userid, userdetails.getUsername(), null, token, role);
		} else {
			throw new AppUserNotFoundException(NotValid);
		}
	}
}