package com.cognizant.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.account.exception.AccountNotFoundException;
import com.cognizant.account.feign.AuthFeign;
import com.cognizant.account.feign.TranFeign;
import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.AuthenticationResponse;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;
import com.cognizant.account.repository.AccountRepository;

/*
 * The test class for the service class tests.
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTests {

	/*
	 * Mock for the AuthFeign autowiring.
	 */
	@Mock
	AuthFeign authFeignMock;

	/*
	 * Mock for the TranFeign autowiring.
	 */
	@Mock
	TranFeign tranFeignMock;

	/*
	 * Mock for the AccountRepository autowiring.
	 */
	@Mock
	AccountRepository accountRepository;

	/*
	 * Main mock for the service.
	 */
	@InjectMocks
	AccountServiceImpl accountService;

	/*
	 * Test for AccountService -> hasPermission(token).
	 */
	@Test
	public void hasPermissionTest() {
		AuthenticationResponse authRes = new AuthenticationResponse("User1", "Ivan", true);
		when(authFeignMock.getValidity("token")).thenReturn(authRes);

		assertEquals(authRes.getUserid(), accountService.hasPermission("token").getUserid());
		assertEquals(authRes.getName(), accountService.hasPermission("token").getName());
	}

	/*
	 * Test for AccountService -> createAccount(customerId, account).
	 */
	@Test
	public void createAccountTest() {
		Account acc = new Account(1L, "123", 1000, "Savings", "Ivan");

		assertEquals(1L, accountService.createAccount("123", acc).getAccountId());
		assertEquals("Account Created Successfully.", accountService.createAccount("123", acc).getMessage());
	}

	/*
	 * Test for AccountService -> getCustomerAccounts(customerId).
	 */
	@Test
	public void getCustomerAccountsTest() {
		Account acc = new Account(1L, "123", 1000, "Savings", "Ivan");
		List<Account> accList = new ArrayList<>();
		accList.add(acc);
		when(accountRepository.findByCustomerId("123")).thenReturn(accList);

		assertEquals(1, accountService.getCustomerAccounts("123").size());
	}

	/*
	 * Test for AccountService -> getAllAccounts().
	 */
	@Test
	public void getAllAccountsTest() {
		Account acc1 = new Account(1L, "123", 1000, "Savings", "Ivan");
		Account acc2 = new Account(2L, "456", 500, "Savings", "Neil");
		List<Account> accList = new ArrayList<>();
		accList.add(acc1);
		accList.add(acc2);

		when(accountRepository.findAll()).thenReturn(accList);

		assertEquals(2, accountService.getAllAccounts().size());

	}

	/*
	 * Test for AccountService -> getAccount(accountId).
	 */
	@Test
	public void getAccountTest() {
		Account acc = new Account(1L, "123", 1000, "Savings", "Ivan");
		// AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(accountRepository.findByAccountId(1L)).thenReturn(acc);

		assertEquals(acc.getAccountId(), accountService.getAccount(1L).getAccountId());
		assertEquals(acc.getBalance(), accountService.getAccount(1L).getBalance());
	}

	/*
	 * Test for AccountService -> deleteAccountByCustomerId(customerId).
	 */
	@Test
	public void deleteAccountByCustomerIdTest() {
		accountService.deleteAccountsByCustomerId("123L");
	}

	/*
	 * Test for AccountService -> deposit(accountInfo).
	 */
	@Test
	public void depositTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(tranFeignMock.makeDeposit("token", accInfo)).thenReturn(new TransactionStatus("Deposited.", 0, 0));
		assertEquals("Deposited.", accountService.deposit("token", accInfo).getMessage());
	}

	/*
	 * Test for AccountService -> withdraw(accountInfo).
	 */
	@Test
	public void withdrawTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(tranFeignMock.withdraw("token", accInfo)).thenReturn(new TransactionStatus("Withdrawed.", 0, 0));
		assertEquals("Withdrawed.", accountService.withdraw("token", accInfo).getMessage());
	}

	/*
	 * Test for AccountService -> transfer(TransactionInput).
	 */
	@Test
	public void transferTest() {
		TransactionInput tranInput = new TransactionInput(6L, 1L, 1000.0, "Transfer");
		TransactionStatus tranStatus = new TransactionStatus("Transfered.", 6000, 5000);
		when(tranFeignMock.makeTransfer("token", tranInput))
				.thenReturn(new ResponseEntity<TransactionStatus>(tranStatus, HttpStatus.OK));
		assertEquals("Transfered.", accountService.transfer("token", tranInput).getBody().getMessage());
	}

	/*
	 * Test for AccountService -> oupdateAccountById(accountId, newBalance).
	 */
	@Test
	public void updateBalanceTest() {
		Account acc = new Account(1L, "123", 1000, "Savings", "Ivan");
		when(accountRepository.findByAccountId(1L)).thenReturn(acc);

		assertEquals(1000, accountService.updateBalance("token", 1L, 1000).getBalance());
	}

	/*
	 * Test for AccountService -> getAccountStatement(accountId, from_date,
	 * to_date).
	 */
	@Test
	public void accountStatementTest() {
		List<Transaction> tranList = new ArrayList<>();
		tranList.add(new Transaction());
		when(tranFeignMock.getTransactionsByAccIdAndDate("token", 1L, null, null)).thenReturn(tranList);

		assertEquals(1, accountService.getAccountStatement("token", 1L, null, null).size());

	}
}
