package com.cognizant.transactionservice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.transactionservice.feign.AccountFeign;
import com.cognizant.transactionservice.feign.RulesFeign;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionStatus;
import com.cognizant.transactionservice.service.TransactionService;
import com.cognizant.transactionservice.util.AccountInfo;
import com.cognizant.transactionservice.util.TransactionInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class TransactionRestController {


	@Autowired
	AccountFeign accountFeign;

	@Autowired
	RulesFeign rulesFeign;

	@Autowired
	TransactionService transactionService;

	/**
	 * Deposits the Amount
	 * 
	 * @param token
	 * @param AccountID
	 * @param amount
	 * @return Transaction Status
	 */

	@PostMapping(value = "/deposit")
	public ResponseEntity<TransactionStatus> makeDeposit(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInfo accountInfo) {

		transactionService.hasPermission(token);

		TransactionStatus tranStatus = transactionService.makeDeposit(token, accountInfo);
		return new ResponseEntity<>(tranStatus, HttpStatus.OK);
	}

	/**
	 * Withdraw the Amount
	 * 
	 * @param token
	 * @param AccountID 
	 * @param amount 
	 * @return Transaction Status 
	 */

	@PostMapping(value = "/withdraw")
	public ResponseEntity<TransactionStatus> makeWithdraw(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInfo accountInfo) {

		transactionService.hasPermission(token);

		TransactionStatus tranStatus = transactionService.makeWithdraw(token, accountInfo);
		return new ResponseEntity<>(tranStatus, HttpStatus.OK);
	}

	/**
	 * Deduct the Amount For Service Charge
	 * 
	 * @param token
	 * @param AccountID 
	 * @param amount   
	 * @return Transaction Status
	 */

	@PostMapping(value = "/deduct")
	public ResponseEntity<TransactionStatus> deduct(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInfo accountInfo) {

		transactionService.hasPermission(token);

		TransactionStatus tranStatus = transactionService.makeServiceCharges(token, accountInfo);
		return new ResponseEntity<>(tranStatus, HttpStatus.OK);
	}

	/**
	 * Transfer the Amount
	 * 
	 * @param token
	 * @param Source_AccountID 
	 * @param Target_AccountID 
	 * @param amount        
	 * @return Transaction Status 
	 */

	@PostMapping(value = "/transfer")
	public ResponseEntity<TransactionStatus> makeTransfer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody TransactionInput transactionInput) {

		transactionService.hasPermission(token);

		log.info("inside transaction method");
		if (transactionInput != null) {
			TransactionStatus tranStatus = transactionService.makeTransfer(token, transactionInput);

			return new ResponseEntity<>(tranStatus, HttpStatus.OK);
		} else {
			return null;
		}
	}


	/**
	 * Get Account Statement (AccountFeign)
	 * @param token
	 * @param Account Id
	 * @param from_date
	 * @param to_date
	 * @return Transaction Statements 
	 */
	@GetMapping(value = "/getAllTransByAccIdAndDate/{accountId}")
	public List<Transaction> getTransactionsByAccIdAndDate(@RequestHeader("Authorization") String token,
			@PathVariable("accountId") long accId, @RequestParam String from_date, @RequestParam String to_date) {

		transactionService.hasPermission(token);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
		LocalDate from=LocalDate.parse(from_date,formatter);
		LocalDate to=LocalDate.parse(to_date,formatter);
		List<Transaction> tranList = transactionService.getTransactionsByAccIdAndDate(accId, from, to);
		return tranList;
	}
	
	/**
	 * Get Transactions
	 * @param token
	 * @param Account Id
	 * @return Transaction Statements 
	 */
	@GetMapping(value = "/getAllTrans/{accountId}")
	public List<Transaction> getTransactionsByAcc(@RequestHeader("Authorization") String token,
			@PathVariable("accountId") long accId) {

		transactionService.hasPermission(token);
		List<Transaction> tranList = transactionService.getTransactionsByAccId(accId);
		return tranList;
	}

}
