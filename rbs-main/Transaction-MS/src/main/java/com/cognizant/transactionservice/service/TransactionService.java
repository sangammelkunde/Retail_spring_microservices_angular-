package com.cognizant.transactionservice.service;

import java.time.LocalDate;
import java.util.List;

import com.cognizant.transactionservice.models.AuthenticationResponse;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionStatus;
import com.cognizant.transactionservice.util.AccountInfo;
import com.cognizant.transactionservice.util.TransactionInput;



public interface TransactionService {

	public AuthenticationResponse hasPermission(String token);

	public TransactionStatus makeDeposit(String token, AccountInfo accountInfo);

	public TransactionStatus makeWithdraw(String token, AccountInfo accountInfo);

	public TransactionStatus makeTransfer(String token, TransactionInput transactionInput);

	public List<Transaction> getTransactionsByAccIdAndDate(long accountId, LocalDate from_date, LocalDate to_date);
	
	public List<Transaction> getTransactionsByAccId(long accountId);

	public TransactionStatus makeServiceCharges(String token, AccountInfo accountInfo);
}
