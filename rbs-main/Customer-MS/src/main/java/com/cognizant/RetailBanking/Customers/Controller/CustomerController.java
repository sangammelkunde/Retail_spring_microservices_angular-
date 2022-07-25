package com.cognizant.RetailBanking.Customers.Controller;

import java.net.BindException;
import java.time.DateTimeException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.RetailBanking.Customers.feign.AuthorizationFeign;
import com.cognizant.RetailBanking.Customers.model.AccountCreationStatus;
import com.cognizant.RetailBanking.Customers.model.AppUser;
import com.cognizant.RetailBanking.Customers.model.CustomerEntity;
import com.cognizant.RetailBanking.Customers.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	AuthorizationFeign authFeign;

	/**
	 * 
	 * @param appUserloginCredentials
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<AppUser> authenticate(@RequestBody AppUser appUserloginCredentials) {
		return authFeign.login(appUserloginCredentials);
	}

	/**
	 * 
	 * @param token
	 * @param customer
	 * @return
	 * @throws DateTimeException
	 * @throws BindException
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<AccountCreationStatus> createCustomer(@RequestHeader("Authorization") String token,
			@RequestBody CustomerEntity customer) throws DateTimeException, BindException {

		customerService.hasEmployeePermission(token);
		AccountCreationStatus status = customerService.createCustomer(token, customer);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

	}

	/**
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */

	@PostMapping("/updateCustomer")
	public CustomerEntity updateCustomer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		return customerService.updateCustomer(token, customer);
	}

	/**
	 * @get customer details by customer id
	 * @param token
	 * @param customerId
	 * @return
	 */
	@GetMapping("/getCustomerDetails/{customerId}")
	public ResponseEntity<CustomerEntity> getCustomerDetails(@RequestHeader("Authorization") String token,
			@PathVariable String customerId) {
		customerService.hasPermission(token);
		CustomerEntity returnCustomerDetails = customerService.getCustomerDetail(token, customerId);
		if (returnCustomerDetails == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		returnCustomerDetails.setPassword(null);
		return new ResponseEntity<>(returnCustomerDetails, HttpStatus.OK);
	}

	/**
	 * @delete customer
	 * @param token
	 * @param customerId
	 * @return
	 */
	@DeleteMapping("/deleteCustomer/{customerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token,
			@PathVariable String customerId) {

		customerService.hasEmployeePermission(token);
		CustomerEntity checkCustomerIdExists = null;
		checkCustomerIdExists = customerService.getCustomerDetail(token, customerId);
		if (checkCustomerIdExists == null) {
			return new ResponseEntity<>("Customer Userid DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		}

		System.out.println("Starting deletion of-->" + customerId);
		customerService.deleteCustomer(token, customerId);
		System.out.println("Deleted");
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);

	}

	/**
	 * @deduct service charges
	 * @param token
	 * @return
	 */
	@GetMapping("/deductServiceCharges")
	public int deductServiceCharges(@RequestHeader("Authorization") String token) {
		customerService.hasEmployeePermission(token);

		return customerService.deductServiceCharges(token);
	}

	/**
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */

	@PostMapping("/saveCustomer")
	public CustomerEntity saveCustomer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		CustomerEntity customerEntity = customerService.saveCustomer(token, customer);

		if (customerEntity != null)
			return customerEntity;

		else
			return null;
	}

}
