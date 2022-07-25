package com.cognizant.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountInfo;
import com.cognizant.account.model.AuthenticationResponse;
import com.cognizant.account.model.Transaction;
import com.cognizant.account.model.TransactionInput;
import com.cognizant.account.model.TransactionStatus;
import com.cognizant.account.service.AccountService;

/*
 * The test class for controller test cases.
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTests {

	/*
	 * Mock for the AccountService autowiring.
	 */
	@Mock
	AccountService accountService;

	/*
	 * Main mock for the controller.
	 */
	@InjectMocks
	AccountController accountController;

	/*
	 * Test for the has permission call of the controller.
	 */
	@Test
	public void hasPermissionTest() {
		AuthenticationResponse authRes = new AuthenticationResponse("User1", "Ivan", true);
		when(accountService.hasPermission("token")).thenReturn(authRes);

		assertEquals(authRes.getUserid(), accountService.hasPermission("token").getUserid());
		assertEquals(authRes.getName(), accountService.hasPermission("token").getName());
	}

	/*
	 * Test for the getAccount method.
	 */
	@Test
	public void getAccountTest() {
		Account acc = new Account(1L, "123L", 1000, "Savings", "Ivan");
		when(accountService.getAccount(1)).thenReturn(acc);
		assertEquals("Ivan", accountController.getAccount("token", 1).getBody().getOwnerName());
	}

	/*
	 * Test for the getCustomerAccounts method.
	 */
	@Test
	public void getCustomerAccountsTest() {
		Account acc = new Account(1L, "123L", 1000, "Savings", "Ivan");
		List<Account> accList = new ArrayList<>();
		accList.add(acc);

		when(accountService.getCustomerAccounts("123L")).thenReturn(accList);

		assertEquals(1, accountController.getCustomerAccounts("token", "123L").size());
	}

	/*
	 * Test for the getAllAccounts method.
	 */
	@Test
	public void getAllAccountsTest() {
		Account acc = new Account(1L, "123L", 1000, "Savings", "Ivan");
		List<Account> accList = new ArrayList<>();
		accList.add(acc);

		when(accountService.getAllAccounts()).thenReturn(accList);
		assertEquals(1, accountController.getAllAccounts("token").size());
	}

	/*
	 * Test for the deposit method.
	 */
	@Test
	public void depositTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(accountService.deposit("token", accInfo)).thenReturn(new TransactionStatus("Deposited.", 0, 0));

		assertEquals("Deposited.", accountController.deposit("token", accInfo).getMessage());
	}

	/*
	 * Test for the withdraw method.
	 */
	@Test
	public void withdrawTest() {
		AccountInfo accInfo = new AccountInfo(1L, 1000);
		when(accountService.withdraw("token", accInfo)).thenReturn(new TransactionStatus("Withdrawed.", 0, 0));
		assertEquals("Withdrawed.", accountController.withdraw("token", accInfo).getMessage());
	}

	/*
	 * Test for the transfer method.
	 */
	@Test
	public void transferTest() {
		TransactionInput tranInput = new TransactionInput(6L, 1L, 1000.0, "Transfer");
		TransactionStatus tranStatus = new TransactionStatus("Transfered.", 6000, 5000);
		when(accountService.transfer("token", tranInput))
				.thenReturn(new ResponseEntity<TransactionStatus>(tranStatus, HttpStatus.OK));
		assertEquals("Transfered.", accountController.transfer("token", tranInput).getBody().getMessage());
	}

	/*
	 * Test for the updateAccountById method.
	 */
	@Test
	public void updateAccountByIdTest() {
		Account acc = new Account(1L, "123L", 1000, "Savings", "Ivan");
		when(accountService.updateBalance("token", 1L, 1000)).thenReturn(acc);
		assertEquals(1000, accountController.updateAccountById("token", 1L, 1000).getBalance());
	}

	/*
	 * Test for the getAccountStatement method.
	 */
	@Test
	public void accountStatementTest(){
		when(accountService.getAccountStatement("token", 1L, LocalDate.parse("2021-06-28"),LocalDate.parse("2021-07-28"))).thenReturn(new ArrayList<Transaction>());
		assertEquals(0, accountController.getAccountStatement("token", 1L, "2021-06-28", "2021-07-28").size());
	}
	
	/*
	 * Test for the checkBalance method.
	 */
	@Test
	public void checkBalanceTest(){
		when(accountService.getAccount(1L)).thenReturn(new Account(1L,"Cust101",5000.00,"Savings","Kevin"));
		assertEquals(5000.00, accountController.checkBalance("token",1L));
	}
}
