package com.cognizant.account.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountCreationStatus;
import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.AuthenticationResponse;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;

/*
 * The service iterface for the service component.
 */

public interface AccountService {

	public AuthenticationResponse hasPermission(String token);

	public AccountCreationStatus createAccount(String customerId, Account account);

	public List<Account> getCustomerAccounts(String customerId);

	public List<Account> getAllAccounts();

	public Account getAccount(long accountId);

	public List<Transaction> getAccountStatement(String token, long accountId, LocalDate from_date, LocalDate to_date);

	public void deleteAccountsByCustomerId(String customerId);

	public TransactionStatus deposit(String token, AccountInfo accountInfo);

	public TransactionStatus withdraw(String token, AccountInfo accountInfo);

	public ResponseEntity<TransactionStatus> deduct(String token, AccountInfo accountInfo);

	public ResponseEntity<TransactionStatus> transfer(String token, TransactionInput transactionInput);

	public Account updateBalance(String token, long accountId, double newBalance);

}
