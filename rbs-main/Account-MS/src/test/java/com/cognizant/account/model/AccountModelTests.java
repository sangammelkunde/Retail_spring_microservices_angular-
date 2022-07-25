package com.cognizant.account.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/*
 * The test class for model classes tests.
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountModelTests {

	/*
	 * Test for the Account class.
	 */
	@Test
	public void testAccount() {
		Account acc = new Account();
		acc.setAccountId(1L);
		acc.setCustomerId("CUST101");
		acc.setBalance(1000);
		acc.setAccountType("Savings");
		acc.setOwnerName("Ivan");

		assertEquals(1L, acc.getAccountId());
		assertEquals("CUST101", acc.getCustomerId());
		assertEquals(1000, acc.getBalance());
		assertEquals("Savings", acc.getAccountType());
		assertEquals("Ivan", acc.getOwnerName());
	}

	/*
	 * Test for the Transaction class.
	 */
	@Test
	public void testTransaction() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Transaction transac = new Transaction(0, 0, null, 0, null, 0, null, null);
		Transaction tran = new Transaction();
		tran.setId(1L);
		tran.setSourceAccountId(1L);
		tran.setTargetAccountId(1L);
		tran.setSourceOwnerName("Ivan");
		tran.setTargetOwnerName("Ivan");
		tran.setAmount(100);
		tran.setDateOfTransaction(LocalDate.parse("05-03-2021", format));
		tran.setTypeOfTransaction("Deposit");

		assertEquals(1L, tran.getId());
		assertEquals(1L, tran.getSourceAccountId());
		assertEquals(1L, tran.getTargetAccountId());
		assertEquals("Ivan", tran.getSourceOwnerName());
		assertEquals("Ivan", tran.getTargetOwnerName());
		assertEquals(100, tran.getAmount());
		assertEquals(LocalDate.parse("05-03-2021", format), tran.getDateOfTransaction());
		assertEquals("Deposit", tran.getTypeOfTransaction());
	}

	/*
	 * Test for the AppUser class.
	 */
	@Test
	public void testAppUser() {
		AppUser user1 = new AppUser(null, null, null, null, null);
		AppUser user = new AppUser();

		user.setUserid("CUST101");
		user.setUsername("Ivan");
		user.setPassword("pass");
		user.setAuthToken("token");
		user.setRole("CUSTOMER");

		assertEquals("CUST101", user.getUserid());
		assertEquals("Ivan", user.getUsername());
		assertEquals("pass", user.getPassword());
		assertEquals("token", user.getAuthToken());
		assertEquals("CUSTOMER", user.getRole());
	}

	/*
	 * Test for the TransactionInput class.
	 */
	@Test
	public void testTransactionInput() {
		TransactionInput tranInput = new TransactionInput();
		tranInput.setSourceAccount(1L);
		tranInput.setTargetAccount(2L);
		tranInput.setAmount(100);
		tranInput.setTypeOfTrasaction("Transfer.");

		assertEquals(1L, tranInput.getSourceAccount());
		assertEquals(2L, tranInput.getTargetAccount());
		assertEquals(100, tranInput.getAmount());
		assertEquals("Transfer.", tranInput.getTypeOfTrasaction());

	}

	/*
	 * Test for the TransactionStatus class.
	 */
	@Test
	public void testTransactionStatus() {
		TransactionStatus status = new TransactionStatus();
		status.setMessage("Deposit.");
		status.setFinalBalance(900);
		status.setInitialBalance(1000);

		assertEquals("Deposit.", status.getMessage());
		assertEquals(1000, status.getInitialBalance());
		assertEquals(900, status.getFinalBalance());
	}

	/*
	 * Test for the AccountInfo class.
	 */
	@Test
	public void testAccountInfo() {
		AccountInfo acc = new AccountInfo();
		acc.setAccountId(1L);
		acc.setBalance(1000);

		assertEquals(1L, acc.getAccountId());
		assertEquals(1000, acc.getBalance());
	}

	/*
	 * Test for the AccountCreationStatus class.
	 */
	@Test
	public void testAccountCreationStatus() {
		AccountCreationStatus crr = new AccountCreationStatus(null);
		AccountCreationStatus acc = new AccountCreationStatus();
		acc.setAccountId(1L);
		acc.setMessage("Created.");

		assertEquals(1L, acc.getAccountId());
		assertEquals("Created.", acc.getMessage());
	}

	/*
	 * Test for the AuthenticationResponse class.
	 */
	@Test
	public void testAuthenticationResponse() {
		AuthenticationResponse auth = new AuthenticationResponse();
		auth.setName("Ivan");
		auth.setUserid("CUST101");
		auth.setValid(true);

		assertEquals("Ivan", auth.getName());
		assertEquals("CUST101", auth.getUserid());
		assertTrue(auth.isValid());

	}

}
