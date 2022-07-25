package com.cognizant.RetailBanking.Customers.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.RetailBanking.Customers.model.Account;
import com.cognizant.RetailBanking.Customers.model.AccountCreationStatus;
import com.cognizant.RetailBanking.Customers.model.AccountInfo;
import com.cognizant.RetailBanking.Customers.model.TransactionStatus;

@FeignClient(name = "account-ms", url="${feign.url-account-ms}")
public interface AccountFeign {

	
	/**
	 * @CREATE CUSTOMER ACCOUNT
	 * @param token
	 * @param customerId
	 * @param account
	 * @return
	 */
	@PostMapping("/createAccount/{customerId}")
	public AccountCreationStatus createAccount(@RequestHeader("Authorization") String token,
	@PathVariable String customerId, @RequestBody Account account);
	
	/**
	 * @GET CUSTOMER ACCOUNT
	 * @param token
	 * @param customerId
	 * @return
	 */
	@GetMapping("/getCustomerAccounts/{customerId}")
	public List<Account> getCustomerAccount(@RequestHeader("Authorization") String token,
			@PathVariable String customerId);
	
	
	/**
	 * @DELETE CUSTOMER ACCOUNT BY CUSTOMERID
	 * @param token
	 * @param customerId
	 */
	@DeleteMapping("/deleteAccountsByCustomerId/{customerId}")
	public void deleteAccountsByCustomerId(@RequestHeader("Authorization") String token, @PathVariable String customerId);
	
	
	/**
	 * @GET ALL ACCOUNTS 
	 * @param token
	 * @return
	 */
	@GetMapping("/getAllAccounts")
	public List<Account> getAllAccounts(@RequestHeader("Authorization") String token);
	
	
	/**
	 * 
	 * @param token
	 * @param accountInfo
	 * @return
	 */
	@PostMapping("/deduct")
	public ResponseEntity<TransactionStatus> deduct(@RequestHeader("Authorization") String token, @RequestBody AccountInfo accountInfo);
	
}
