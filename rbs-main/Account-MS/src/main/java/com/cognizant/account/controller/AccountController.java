package com.cognizant.account.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountCreationStatus;
import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;
import com.cognizant.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/*
 * Controller class of the account-ms. 
 * This class maps all the requests made to the account-ms and provides appropriate response.
 */


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	AccountService accountService;

	/*
	 * Method to create account for the given customer Id.
	 * POST: /createAccount (Input: CustomerId) | Output: AccountCreationStatus .
	 * @Param: token, customerId, account.
	 * @Return: AccountCreationStatus.
	 */
	@PostMapping("/createAccount/{customerId}")
	public AccountCreationStatus createAccount(@RequestHeader("Authorization") String token,
			@PathVariable("customerId") String customerId, @RequestBody Account account) {

		accountService.hasPermission(token);

		log.info("Start: Creating account.");
		AccountCreationStatus accountCreationStatus = accountService.createAccount(customerId, account);

		if (accountCreationStatus == null) {
			return new AccountCreationStatus("Unsucessful.");
		}

		log.info("End:. Account Created.");

		return accountCreationStatus;

	}

	/*
	 * Method to get accounts of a particular customer.
	 * GET: /getCustomerAccounts (Input:CustomerId | Output: Array of Account(id, Balance) .
	 * @Param: token, customerId.
	 * @Return: List<Account>.
	 */
	@GetMapping("/getCustomerAccounts/{customerId}")
	public List<Account> getCustomerAccounts(@RequestHeader("Authorization") String token,
			@PathVariable String customerId) {

		accountService.hasPermission(token);

		log.info("Start: Fetching Customer Accounts.");
		List<Account> accountList = accountService.getCustomerAccounts(customerId);
		log.info("End.");

		return accountList;

	}

	/*
	 * Method to get particular account based on the account Id. GET:
	 * /getAccount(Input:AccountId | Output: Account(id, balance) .
	 * @Param: token, accountId.
	 * @Return: ResponseEntity<Account>.
	 */
	@GetMapping("/getAccount/{accountId}")
	public ResponseEntity<Account> getAccount(@RequestHeader("Authorization") String token,
			@PathVariable long accountId) {
		accountService.hasPermission(token);

		log.info("Start: Fetching Account Details.");
		Account account = null;
		account = accountService.getAccount(accountId);
		log.info("End.");

		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}

	/*
	 * Method to get all accounts. GET: /getAllAccounts .
	 * @Param: token.
	 * @Return: List<Account>.
	 */
	@GetMapping("/getAllAccounts")
	public List<Account> getAllAccounts(@RequestHeader("Authorization") String token) {

		accountService.hasPermission(token);

		log.info("Start: Fetching All Account Details.");
		List<Account> accountList = accountService.getAllAccounts();
		log.info("End.");

		return accountList;
	}

	/*
	 * Method to delete all accounts of a customer based on customer Id.
	 * DELETE:/deleteAccountsByCustomerId/{customerId}.
	 * @Param: token, customerId.
	 * @Return: null.
	 */
	@DeleteMapping("/deleteAccountsByCustomerId/{customerId}")
	public void deleteAccountsByCustomerId(@RequestHeader("Authorization") String token,
			@PathVariable String customerId) {

		accountService.hasPermission(token);
		log.info("Start: Deleting All Accounts of the Customer.");
		accountService.deleteAccountsByCustomerId(customerId);
		log.info("End.");
	}

	/*
	 * Method to get account statement for a given account Id between the given dates. 
	 * GET:/getAccountStatement(Input: AccountId, from_date, to_date | Output: Statement .
	 * @Param: token, accountId, from_date, to_date;
	 * @Return: List<Transaction>.
	 */
	@GetMapping("/getAccountStatement/{accountId}")
	public List<Transaction> getAccountStatement(@RequestHeader("Authorization") String token,
			@PathVariable long accountId,
//			@RequestParam(defaultValue = "#{T(java.time.LocalDate).now().minusMonths(1)}") LocalDate from_date,
//			@RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate to_date) {
			@RequestParam String from_date,
			@RequestParam String to_date) {

		accountService.hasPermission(token);
		log.info("Start: Fetching Account Statement.");
		LocalDate from=LocalDate.parse(from_date);
		LocalDate to=LocalDate.parse(to_date);
		List<Transaction> transactionList = accountService.getAccountStatement(token, accountId, from, to);
		log.info("End.");

		return transactionList;

	}

	/*
	 * Method to deposit amount for the account. POST:/deposit(Input: AccountId, amount | Output: TransactionStatus) .
	 * @Param: token, accountInfo.
	 * @Return: TransactionStatus.
	 */
	@PostMapping("/deposit")
	public TransactionStatus deposit(@RequestHeader("Authorization") String token,
			@RequestBody AccountInfo accountInfo) {

		accountService.hasPermission(token);
		log.info("Start: Deposit.");
		TransactionStatus transactionStatus = accountService.deposit(token, accountInfo);
		log.info("End.");
		return transactionStatus;
	}

	/*
	 * Method to withdraw amount from the account. POST:/withdraw(Input: AccountId, amount | Output: TransactionStatus) .
	 * @Param: token, accountInfo.
	 * @Return: TransactionStatus.
	 */
	@PostMapping("/withdraw")
	public TransactionStatus withdraw(@RequestHeader("Authorization") String token,
			@RequestBody AccountInfo accountInfo) {

		accountService.hasPermission(token);

		log.info("Start: Withdraw.");
		TransactionStatus transactionStatus = accountService.withdraw(token, accountInfo);
		log.info("End.");
		return transactionStatus;
	}

	/*
	 * Method to transfer amount between the accounts. POST:/transfer(Input: TransactionInput | Output: TransactionStatus) .
	 * @Param: token, transactionInput.
	 * @Return: TransactionStatus
	 */
	@PostMapping("/transfer")
	public ResponseEntity<TransactionStatus> transfer(@RequestHeader("Authorization") String token,
			@RequestBody TransactionInput transactionInput) {

		accountService.hasPermission(token);

		log.info("Start: Transfer.");
		ResponseEntity<TransactionStatus> responseEntity = accountService.transfer(token, transactionInput);
		log.info("End.");
		return responseEntity;
	}

	/*
	 * Method to deduct amount from the account. POST:/deduct(Input: AccountId, amount | Output: TransactionStatus) .
	 * @Param: token, accountInfo.
	 * @Return: ResponseEntity<TransactionStatus>.
	 */
	@PostMapping("/deduct")
	public ResponseEntity<TransactionStatus> deduct(@RequestHeader("Authorization") String token,
			@RequestBody AccountInfo accountInfo) {

		accountService.hasPermission(token);

		log.info("Start: Withdraw.");
		ResponseEntity<TransactionStatus> responseEntity = accountService.deduct(token, accountInfo);
		log.info("End.");
		return responseEntity;
	}

	/*
	 * Method to update account balance. POST:/updateAccountById(Input: AccountId |
	 * Output: Account) .
	 * 
	 * @Param: token, accountId, newBalance.
	 * 
	 * @Return: Account.
	 */
	@PostMapping("/updateAccountById/{accountId}")
	public Account updateAccountById(@RequestHeader("Authorization") String token,
			@PathVariable("accountId") long accountId, @RequestParam double newBalance) {

		accountService.hasPermission(token);

		log.info("Start: Updating Account.");
		Account acc = accountService.updateBalance(token, accountId, newBalance);
		log.info("End.");
		return acc;
	}
	
	
	/**
	 * Method to retrieve balance of an account
	 * @param token
	 * @param accountId
	 * @return account balance
	 */
	@PostMapping("/checkBalance/{accountId}")
	public double checkBalance(@RequestHeader("Authorization") String token,
			@PathVariable("accountId") long accountId) {

		accountService.hasPermission(token);

		log.info("Start: Checking balance of "+accountId+" account.");
		Account acc = accountService.getAccount(accountId);
		log.info("End.");
		return acc.getBalance();
	}
	
	

}
