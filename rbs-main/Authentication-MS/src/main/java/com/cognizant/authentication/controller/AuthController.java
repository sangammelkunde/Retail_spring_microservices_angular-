package com.cognizant.authentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authentication.exceptionhandling.AppUserNotFoundException;
import com.cognizant.authentication.model.AppUser;
import com.cognizant.authentication.model.AuthenticationResponse;
import com.cognizant.authentication.repository.UserRepository;
import com.cognizant.authentication.service.LoginService;
import com.cognizant.authentication.service.Validationservice;

import lombok.extern.slf4j.Slf4j;

/**
 * The AuthController class for request controller
 * 
 */

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

	/**
	 * Users Repository
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Service class login
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * Service class for login
	 */
	@Autowired
	private Validationservice validationService;

	/**
	 * The login method with post request
	 * 
	 * @param appUserLoginCredentials
	 * @return return user with Http Accepted Status if the login credentials is
	 *         correct
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<AppUser> login(@RequestBody AppUser appUserloginCredentials)
			throws UsernameNotFoundException, AppUserNotFoundException {
		AppUser user = loginService.userLogin(appUserloginCredentials);
		log.info("Credentials ----->{}", user);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

	/**
	 * The token validation method
	 * 
	 * @param token
	 * @return if the token is valid or not
	 * 
	 */
	@GetMapping("/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {
		log.info("Token Validation ----->{}", token);
		return validationService.validate(token);
	}

	/**
	 * The user is created with login credentials
	 * 
	 * @param AppUserCredentials
	 * @return createdUser with Http Status Created
	 * 
	 */
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody AppUser appUserCredentials) {
		AppUser createduser = null;
		try {
			createduser = userRepository.save(appUserCredentials);
		} catch (Exception e) {
			return new ResponseEntity<>(createduser, HttpStatus.NOT_ACCEPTABLE);
		}
		log.info("user creation---->{}", createduser);
		return new ResponseEntity<>(createduser, HttpStatus.CREATED);

	}

	/**
	 * The find users method to find all users
	 * 
	 * @param token
	 * @return all users with Http status created
	 * 
	 */
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	@GetMapping("/find")
	public ResponseEntity<List<AppUser>> findUsers(@RequestHeader("Authorization") final String token) {
		List<AppUser> createduser = new ArrayList<>();
		List<AppUser> findAll = userRepository.findAll();
		findAll.forEach(emp -> createduser.add(emp));
		log.info("All Users  ----->{}", findAll);
		return new ResponseEntity<>(createduser, HttpStatus.CREATED);

	}

	/**
	 * The get role method find the role of the entity
	 * 
	 * @param id
	 * @return role
	 */
	@GetMapping("/role/{id}")
	public String getRole(@PathVariable("id") String id) {
		return userRepository.findById(id).get().getRole();
	}

	/**
	 * The health method to check application
	 */
	@GetMapping("/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("Health Check for Authentication Microservice");
		return new ResponseEntity<>("Status>>>>>>UP", HttpStatus.OK);
	}
}