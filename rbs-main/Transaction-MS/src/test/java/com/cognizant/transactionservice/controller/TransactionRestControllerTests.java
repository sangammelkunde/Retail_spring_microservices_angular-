package com.cognizant.transactionservice.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.transactionservice.exception.GlobalExceptionHandler;
import com.cognizant.transactionservice.exception.MinimumBalanceException;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionStatus;
import com.cognizant.transactionservice.service.TransactionServiceImpl;
import com.cognizant.transactionservice.util.AccountInfo;
import com.cognizant.transactionservice.util.TransactionInput;



@RunWith(MockitoJUnitRunner.class)
public class TransactionRestControllerTests {

	@Mock
	TransactionServiceImpl transactionService;

	@InjectMocks
	TransactionRestController transactionController;

	@InjectMocks
	TransactionStatus tran;

	/**
	 * 
	 * Test Minimum balance
	 *
	 */
	@Test
	public void minimumBal() throws MinimumBalanceException, Exception {
		MinimumBalanceException minimumBalanceException = new MinimumBalanceException();
		MinimumBalanceException minimumBalanceException2 = new MinimumBalanceException("Hi");
		assertNotEquals(minimumBalanceException, minimumBalanceException2);
		GlobalExceptionHandler handler = new GlobalExceptionHandler();

	}

	/**
	 * 
	 * Test Deposit
	 *
	 */
	@Test
	public void depositTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(transactionService.makeDeposit("token", accInfo)).thenReturn(new TransactionStatus("Deposited.", 0, 0));
		assertEquals("Deposited.", transactionController.makeDeposit("token", accInfo).getBody().getMessage());
	}

	/**
	 * 
	 * Test Withdraw
	 *
	 */
	@Test
	public void withdrawTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(transactionService.makeWithdraw("token", accInfo)).thenReturn(new TransactionStatus("Withdrawed.", 0, 0));
		assertEquals("Withdrawed.", transactionController.makeWithdraw("token", accInfo).getBody().getMessage());
	}

	/**
	 * 
	 * Test Transfer
	 *
	 */
	@Test
	public void transferTest() {
		TransactionInput tranInput = new TransactionInput(1L, 2L, 1000, "Transfer.");
		when(transactionService.makeTransfer("token", tranInput))
				.thenReturn(new TransactionStatus("Transfered.", 0, 0));
		assertEquals("Transfered.", transactionController.makeTransfer("token", tranInput).getBody().getMessage());
	}

	/**
	 * 
	 * Test Service Charge Deduction
	 *
	 */
	@Test
	public void deductTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(transactionService.makeServiceCharges("token", accInfo))
				.thenReturn(new TransactionStatus("Service Charge.", 0, 0));
		assertEquals("Service Charge.",
				((TransactionStatus) transactionController.deduct("token", accInfo).getBody()).getMessage());
	}
	
	/**
	 * 
	 * Test get transaction by account id and date
	 *
	 */
	/*@Test
	public void getTransactionsByAccIdAndDateTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
		when(transactionService.getTransactionsByAccIdAndDate(1L,LocalDate.parse("2021-06-28",formatter),LocalDate.parse("2021-07-28",formatter)))
				.thenReturn(new ArrayList<Transaction>());
		assertEquals(0,
				(transactionController.getTransactionsByAccIdAndDate("token",1L,"2021-06-28", "2021-07-28")).size());
	}*/
	
	/**
	 * 
	 * Test get transaction by account id 
	 *
	 */
	@Test
	public void getTransactionsByAccTest() {
		when(transactionService.getTransactionsByAccId(1L))
				.thenReturn(new ArrayList<Transaction>());
		assertEquals(0,
				(transactionController.getTransactionsByAcc("token",1L)).size());
	}
	
	
	
	

}