package com.cognizant.transactionservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cognizant.transactionservice.exception.AccountNotFoundException;
import com.cognizant.transactionservice.exception.InsufficientBalanceException;
import com.cognizant.transactionservice.exception.MinimumBalanceException;
import com.cognizant.transactionservice.feign.AccountFeign;
import com.cognizant.transactionservice.feign.AuthFeign;
import com.cognizant.transactionservice.feign.RulesFeign;
import com.cognizant.transactionservice.models.Account;
import com.cognizant.transactionservice.models.AuthenticationResponse;
import com.cognizant.transactionservice.models.RulesInput;
import com.cognizant.transactionservice.models.RulesStatus;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionStatus;
import com.cognizant.transactionservice.repository.TransactionRepository;
import com.cognizant.transactionservice.util.AccountInfo;
import com.cognizant.transactionservice.util.TransactionInput;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountFeign accountFeign;

	@Autowired
	private AuthFeign authFeign;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private RulesFeign ruleFeign;

	@Value("${minbalance}")
	String minbalance;

	@Value("${minbalancepost}")
	String minbalancepost;

	@Value("${insufficientbalance}")
	String insufficientbalance;

	public AuthenticationResponse hasPermission(String token) {
		return authFeign.getValidity(token);
	}

	/**
	 * Method to Deposit
	 * 
	 * @return
	 */

	@Override
	public TransactionStatus makeDeposit(String token, AccountInfo accountInfo) {
		log.info("method to make a deposit");
		Account sourceAccount = null;

		long accountId = accountInfo.getAccountId();

		sourceAccount = accountFeign.getAccount(token, accountId);

		if (sourceAccount != null) {
			Transaction transaction = new Transaction();
			transaction.setSourceAccountId(sourceAccount.getAccountId());
			transaction.setSourceOwnerName(sourceAccount.getOwnerName());
			transaction.setTargetAccountId(sourceAccount.getAccountId());
			transaction.setTargetOwnerName(sourceAccount.getOwnerName());
			transaction.setDateOfTransaction(LocalDate.now());
			transaction.setTypeOfTransaction("Deposit.");
			transaction.setAmount(accountInfo.getBalance());
			transactionRepository.save(transaction);
			double newBalance = sourceAccount.getBalance() + accountInfo.getBalance();

			Account upSourceAcc = accountFeign.updateAccountById(token, accountId, newBalance);

			return new TransactionStatus("Deposited.", sourceAccount.getBalance(), upSourceAcc.getBalance());
		}
		return null;
	}

	/**
	 * Method to Withdraw
	 * 
	 * @return
	 */
	@Override
	public TransactionStatus makeWithdraw(String token, AccountInfo accountInfo) {
		log.info("method to make a withdraw");
		Account sourceAccount = null;

		long accountId = accountInfo.getAccountId();
		sourceAccount = accountFeign.getAccount(token, accountId);

		if (sourceAccount.getBalance() < accountInfo.getBalance()) {
			throw new InsufficientBalanceException(insufficientbalance);
		}

		RulesStatus ruleStatus = ruleFeign.evaluateMinBalance(sourceAccount.getBalance());
		if (ruleStatus.getStatus().equalsIgnoreCase("DENIED"))
			throw new MinimumBalanceException(minbalance);

		RulesStatus ruleStatusWithdraw = ruleFeign
				.evaluateMinBalance(sourceAccount.getBalance() - accountInfo.getBalance());
		if (ruleStatusWithdraw.getStatus().equalsIgnoreCase("DENIED"))
			throw new MinimumBalanceException(minbalancepost);

		Transaction transaction = new Transaction();
		transaction.setSourceAccountId(sourceAccount.getAccountId());
		transaction.setSourceOwnerName(sourceAccount.getOwnerName());
		transaction.setTargetAccountId(sourceAccount.getAccountId());
		transaction.setTargetOwnerName(sourceAccount.getOwnerName());
		transaction.setDateOfTransaction(LocalDate.now());
		transaction.setTypeOfTransaction("Withdrawn.");
		transaction.setAmount(accountInfo.getBalance());
		transactionRepository.save(transaction);
		double newBalance = sourceAccount.getBalance() - accountInfo.getBalance();

		Account upSourceAcc = accountFeign.updateAccountById(token, accountId, newBalance);

		return new TransactionStatus("Withdrawed.", sourceAccount.getBalance(), upSourceAcc.getBalance());

	}

	/**
	 * Method to make a Transfer
	 * 
	 * @return
	 */
	@Override
	public TransactionStatus makeTransfer(String token, TransactionInput transactionInput)
			throws MinimumBalanceException {

		Account sourceAccount = null;
		Account targetAccount = null;

		long sourceAccountNumber = transactionInput.getSourceAccount();
		sourceAccount = accountFeign.getAccount(token, sourceAccountNumber);

		if (sourceAccount.getBalance() < transactionInput.getAmount()) {
			throw new InsufficientBalanceException(insufficientbalance);
		}

		RulesStatus ruleStatus = ruleFeign.evaluateMinBalance(sourceAccount.getBalance());
		if (ruleStatus.getStatus().equalsIgnoreCase("DENIED"))
			throw new MinimumBalanceException(minbalance);
		double amt = (sourceAccount.getBalance() - transactionInput.getAmount());
		RulesStatus ruleStatusWithdraw = ruleFeign.evaluateMinBalance(amt);
		log.info("" + ruleStatus.getStatus());
		if (ruleStatusWithdraw.getStatus().equalsIgnoreCase("DENIED"))
			throw new MinimumBalanceException(minbalancepost);

		long targetAccountNumber = transactionInput.getTargetAccount();
		targetAccount = accountFeign.getAccount(token, targetAccountNumber);

		if (sourceAccount != null && targetAccount != null) {

			Transaction sourcetransaction = new Transaction();

			sourcetransaction.setAmount(transactionInput.getAmount());
			sourcetransaction.setSourceAccountId(sourceAccount.getAccountId());
			sourcetransaction.setSourceOwnerName(sourceAccount.getOwnerName());
			sourcetransaction.setTargetAccountId(targetAccount.getAccountId());
			sourcetransaction.setTargetOwnerName(targetAccount.getOwnerName());
			sourcetransaction.setDateOfTransaction(LocalDate.now());
			sourcetransaction.setTypeOfTransaction("Transfer.");
			transactionRepository.save(sourcetransaction);

			Account upSourceAcc = accountFeign.updateAccountById(token, sourceAccountNumber,
					sourceAccount.getBalance() - transactionInput.getAmount());
			Account upTargetAcc = accountFeign.updateAccountById(token, targetAccountNumber,
					targetAccount.getBalance() + transactionInput.getAmount());

			return new TransactionStatus("Transfered.", sourceAccount.getBalance(), upSourceAcc.getBalance());
		}
		return null;
	}

	/**
	 * Method to get transaction details bteween dates
	 * 
	 * @return
	 */
	@Override
	public List<Transaction> getTransactionsByAccIdAndDate(long accountId, LocalDate from_date, LocalDate to_date) {
		return transactionRepository.findBySourceAccountIdAndDateOfTransactionBetweenOrderByDateOfTransaction(accountId,
				from_date, to_date);
	}
	
	/**
	 * Method to get transaction details
	 * 
	 * @return
	 */
	@Override
	public List<Transaction> getTransactionsByAccId(long accountId) {
		return transactionRepository.findBySourceAccountIdOrderByDateOfTransaction(accountId);
	}

	/**
	 * Method to make service charge deduction
	 * 
	 * @return
	 */
	@Override
	public TransactionStatus makeServiceCharges(String token, AccountInfo accountInfo) {
		log.info("method to make a service charges");
		Account sourceAccount = null;

		long accountId = accountInfo.getAccountId();
		sourceAccount = accountFeign.getAccount(token, accountId);
		if (sourceAccount != null) {
			Transaction transaction = new Transaction();
			transaction.setSourceAccountId(sourceAccount.getAccountId());
			transaction.setSourceOwnerName(sourceAccount.getOwnerName());
			transaction.setTargetAccountId(sourceAccount.getAccountId());
			transaction.setTargetOwnerName(sourceAccount.getOwnerName());
			transaction.setDateOfTransaction(LocalDate.now());
			transaction.setTypeOfTransaction("Service charge.");
			transaction.setAmount(accountInfo.getBalance());
			transactionRepository.save(transaction);

			double newBalance = sourceAccount.getBalance() - accountInfo.getBalance();

			Account upSourceAcc = accountFeign.updateAccountById(token, accountId, newBalance);

			return new TransactionStatus("Service Charge.", sourceAccount.getBalance(), upSourceAcc.getBalance());
		}

		return null;

	}

}
