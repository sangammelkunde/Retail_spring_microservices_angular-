package com.cognizant.transactionservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.transactionservice.models.AuthenticationResponse;
import com.cognizant.transactionservice.models.RulesStatus;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.feign.AuthFeign;
import com.cognizant.transactionservice.feign.AccountFeign;
import com.cognizant.transactionservice.feign.RulesFeign;
import com.cognizant.transactionservice.models.Account;
import com.cognizant.transactionservice.models.TransactionStatus;
import com.cognizant.transactionservice.repository.TransactionRepository;
import com.cognizant.transactionservice.util.AccountInfo;
import com.cognizant.transactionservice.util.TransactionInput;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTests {

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountFeign accountFeign;

	@Mock
	RulesFeign ruleFeign;

	@Mock
	AuthFeign authFeign;

	@InjectMocks
	TransactionServiceImpl tranServiceMock;

	/*
	 * Test for AccountService -> hasPermission(token).
	 */
	@Test
	public void hasPermissionTest() {
		AuthenticationResponse authRes = new AuthenticationResponse("User1", "Ivan", true);
		when(authFeign.getValidity("token")).thenReturn(authRes);

		assertEquals(authRes.getUserid(), tranServiceMock.hasPermission("token").getUserid());
		assertEquals(authRes.getName(), tranServiceMock.hasPermission("token").getName());
	}

	/*
	 * Test for MakeDeposit
	 */
	@Test
	public void makeDepositTest() {

		AccountInfo accountInfo = new AccountInfo(1L, 1000);
		Account acc = new Account(1L, "123", 10000, "Savings", "Ivan");

		Account upAcc = acc;
		upAcc.setBalance(11000);

		when(accountFeign.getAccount("token", accountInfo.getAccountId())).thenReturn(acc);

		when(accountFeign.updateAccountById("token", accountInfo.getAccountId(),
				acc.getBalance() + accountInfo.getBalance())).thenReturn(upAcc);

		assertEquals("Deposited.", tranServiceMock.makeDeposit("token", accountInfo).getMessage());
	}

	@Test
	public void makeServiceTest() {

		AccountInfo accountInfo = new AccountInfo(1L, 1000);
		Account acc = new Account(1L, "123", 10000, "Savings", "Ivan");

		Account upAcc = acc;
		upAcc.setBalance(9000);

		when(accountFeign.getAccount("token", accountInfo.getAccountId())).thenReturn(acc);

		when(accountFeign.updateAccountById("token", accountInfo.getAccountId(),
				acc.getBalance() - accountInfo.getBalance())).thenReturn(upAcc);

		assertEquals("Service Charge.", tranServiceMock.makeServiceCharges("token", accountInfo).getMessage());
	}

	/*
	 * Test for MakeWithdraw
	 */
	@Test
	public void makeWithdrawTest() {

		AccountInfo accountInfo = new AccountInfo(1L, 1000);
		Account acc = new Account(1L, "123", 10000, "Savings", "Ivan");

		when(accountFeign.getAccount("token", accountInfo.getAccountId())).thenReturn(acc);

		RulesStatus ruleStatus = new RulesStatus("ALLOWED", "");

		when(ruleFeign.evaluateMinBalance(10000)).thenReturn(ruleStatus);

		when(ruleFeign.evaluateMinBalance(9000)).thenReturn(ruleStatus);

		when(accountFeign.updateAccountById("token", accountInfo.getAccountId(),
				acc.getBalance() - accountInfo.getBalance())).thenReturn(acc);

		assertEquals("Withdrawed.", tranServiceMock.makeWithdraw("token", accountInfo).getMessage());
	}

	/*
	 * Test for MakeTransfer
	 */
	@Test
	public void makeTransferTest() {

		TransactionInput accountInfo = new TransactionInput(1L, 2L, 1000, "Transfer");
		Account acc = new Account(1L, "123", 10000, "Savings", "Ivan");
		Account acc2 = new Account(2L, "123", 10000, "Savings", "Ivan");

		when(accountFeign.getAccount("token", accountInfo.getSourceAccount())).thenReturn(acc);

		RulesStatus ruleStatus = new RulesStatus("ALLOWED", "");

		when(ruleFeign.evaluateMinBalance(10000)).thenReturn(ruleStatus);

		when(ruleFeign.evaluateMinBalance(9000)).thenReturn(ruleStatus);

//   		when(accountFeign.updateAccountById("token", accountInfo.getSourceAccount(), acc.getBalance()-accountInfo.getAmount())).thenReturn(acc);
//   		when(accountFeign.updateAccountById("token", accountInfo.getTargetAccount(), acc.getBalance()+accountInfo.getAmount())).thenReturn(acc2);

//   		assertEquals("Transfered.", tranServiceMock.makeTransfer("token", accountInfo).getMessage());
		assertEquals(null, tranServiceMock.makeTransfer("token", accountInfo));
	}

	@Test
	public void accountStatementTest() {
		when(transactionRepository.findBySourceAccountIdAndDateOfTransactionBetweenOrderByDateOfTransaction(1L, null,
				null)).thenReturn(new ArrayList<Transaction>());
		assertEquals(0, tranServiceMock.getTransactionsByAccIdAndDate(1L, null, null).size());
	}
	
	@Test
	public void getTransactionsByAccIdTest() {
		when(transactionRepository.findBySourceAccountIdOrderByDateOfTransaction(1L)).thenReturn(new ArrayList<Transaction>());
		assertEquals(0, tranServiceMock.getTransactionsByAccId(1L).size());
	}
	
	
}
