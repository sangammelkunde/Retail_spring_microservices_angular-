package com.cognizant.account.feign;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;

/*
 * Feign Client for the transaction microservice.
 * This is used to handle all the transaction calls made to the account microservice.
 */

@FeignClient(name = "transaction-ms", url = "${feign.url.transaction-ms}")
public interface TranFeign {

	@GetMapping(value = "/getAllTransByAccIdAndDate/{accountId}")
	public List<Transaction> getTransactionsByAccIdAndDate(@RequestHeader("Authorization") String token,
			@PathVariable("accountId") long accId, @RequestParam LocalDate from_date, @RequestParam LocalDate to_date);

	@PostMapping(value = "/deposit")
	public TransactionStatus makeDeposit(@RequestHeader("Authorization") String token,
			@RequestBody AccountInfo accountInfo);

	@PostMapping(value = "/withdraw")
	public TransactionStatus withdraw(@RequestHeader("Authorization") String token,
			@RequestBody AccountInfo accountInfo);

	@PostMapping(value = "/deduct")
	public ResponseEntity<TransactionStatus> deduct(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInfo accountInfo);

	@PostMapping(value = "/transfer")
	public ResponseEntity<TransactionStatus> makeTransfer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody TransactionInput transactionInput);

}
