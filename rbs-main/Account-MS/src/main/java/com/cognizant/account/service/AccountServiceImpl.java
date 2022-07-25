package com.cognizant.account.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.account.exception.AccountNotFoundException;
import com.cognizant.account.feign.AuthFeign;
import com.cognizant.account.feign.TranFeign;
import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountCreationStatus;
import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.AuthenticationResponse;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;
import com.cognizant.account.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * The service implementation to process all controller requests.
 */

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AuthFeign authFeign;

	@Autowired
	TranFeign tranFeign;

	@Value("${account.notFound}")
	String accountNotFound;

	/*
	 * Method to check the token validity using Authentication feign.
	 * 
	 * @Param: token.
	 * 
	 * @Return: AuthenticationResponse.
	 */
	public AuthenticationResponse hasPermission(String token) {
		return authFeign.getValidity(token);
	}

	/*
	 * Method to create account.
	 * 
	 * @Param: customerId, account.
	 * 
	 * @Return: AccountCreationStatus.
	 */
	@Transactional
	public AccountCreationStatus createAccount(String customerId, Account account) {
		log.info("Start: Create account.");
		accountRepository.save(account);
		AccountCreationStatus accountCreationStatus = new AccountCreationStatus(account.getAccountId(),
				"Account Created Successfully.");
		log.info("End.");
		return accountCreationStatus;

	}

	/*
	 * Method to fetch customer accounts.
	 * 
	 * @Param: customerId.
	 * 
	 * @Return: List<Account>.
	 */
	@Transactional
	public List<Account> getCustomerAccounts(String customerId) {
		log.info("Start: Retrieve accounts for the customer.");
		List<Account> accountList = accountRepository.findByCustomerId(customerId);
		log.info("End.");
		return accountList;
	}

	/*
	 * Method to fetch all accounts.
	 * 
	 * @Param: null.
	 * 
	 * @Return: List<Account>.
	 */
	@Transactional
	public List<Account> getAllAccounts() {
		log.info("Start: Retrive all accounts.");
		log.info("End.");
		List<Account> accList = accountRepository.findAll();
		return accList;
	}

	/*
	 * Method to fetch account.
	 * 
	 * @Param: accountId.
	 * 
	 * @Return: Account.
	 */
	@Transactional
	public Account getAccount(long accountId) {
		log.info("Start: Retrieve account.");
		Account acc = accountRepository.findByAccountId(accountId);
		if (acc == null) {
			throw new AccountNotFoundException(accountNotFound + accountId);
		}
		log.info("End.");
		return acc;
	}

	/*
	 * Method to fetch account statement using the Transaction Feign.
	 * 
	 * @Param: token, accountId, from_date, to_date.
	 * 
	 * @Return: List<Transaction>.
	 */
	@Transactional
	public List<Transaction> getAccountStatement(String token, long accountId, LocalDate from_date, LocalDate to_date) {
		log.info("Start: Get transactions for account.");
		log.info("End.");
		return tranFeign.getTransactionsByAccIdAndDate(token, accountId, from_date, to_date);

	}

	/*
	 * Method to delete customer accounts.
	 * 
	 * @Param: customerId.
	 * 
	 * @Return: null.
	 */
	@Transactional
	public void deleteAccountsByCustomerId(String customerId) {
		log.info("Start: Delete accounts for customer.");
		accountRepository.deleteByCustomerId(customerId);
		log.info("End.");
	}

	/*
	 * Method to deposit to account using Transaction Feign.
	 * 
	 * @Param: token, accountInfo.
	 * 
	 * @Return: TransactionStatus.
	 */
	@Transactional
	public TransactionStatus deposit(String token, AccountInfo accountInfo) {
		log.info("Start: Deposit to account.");
		log.info("End.");
		return tranFeign.makeDeposit(token, accountInfo);
	}

	/*
	 * Method to withdraw from account using Transaction Feign.
	 * 
	 * @Param: token, accountInfo.
	 * 
	 * @Return: TransactionStatus.
	 */
	@Transactional
	public TransactionStatus withdraw(String token, AccountInfo accountInfo) {
		log.info("Start: Withdraw from account.");
		log.info("End.");
		return tranFeign.withdraw(token, accountInfo);
	}

	/*
	 * Method to transfer to account using Transaction Feign.
	 * 
	 * @Param: token, transactionInput.
	 * 
	 * @Return: TransactionStatus.
	 */
	@Transactional
	public ResponseEntity<TransactionStatus> transfer(String token, TransactionInput transactionInput) {
		log.info("Start: Transfer between accounts.");
		log.info("End.");
		return tranFeign.makeTransfer(token, transactionInput);
	}

	/*
	 * Method to deduct from account using Transaction Feign.
	 * 
	 * @Param: token, accountInfo.
	 * 
	 * @Return: ResponseEntity<TransactionStatus>.
	 */
	@Transactional
	public ResponseEntity<TransactionStatus> deduct(String token, AccountInfo accountInfo) {
		log.info("Start: Deduct from account.");
		log.info("End.");
		return tranFeign.deduct(token, accountInfo);
	}

	/*
	 * Method to update account balance.
	 * 
	 * @Param: accountId, newBalance.
	 * 
	 * @Return: Account.
	 */
	@Transactional
	public Account updateBalance(String token, long accountId, double newBalance) {
		log.info("Start: Update balance for account.");
		Account acc = accountRepository.findByAccountId(accountId);
		acc.setBalance(newBalance);
		accountRepository.save(acc);
		log.info("End.");
		return acc;
	}

}
