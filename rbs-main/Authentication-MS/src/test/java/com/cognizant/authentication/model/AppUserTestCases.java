package com.cognizant.authentication.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognizant.authentication.model.AppUserTestCases;

/**
 * AppUser Class Test Cases
 * 
 * @author pod-4
 *
 */
public class AppUserTestCases {

	/**
	 * AppUser Class's Object
	 */
	AppUser app = new AppUser("1", "DummyUser", "employee", "password", "user");
	AppUser AppUserObj = new AppUser();

	/**
	 * AppUser Constructor Test Case
	 */
	@Test
	void AppUserConstructorTest() {
		AppUser AuthUserObj = new AppUser("UserId", "Aryan", "Password", "DemoAuthToken", "Employee");
		assertEquals("UserId", AuthUserObj.getUserid());
		assertEquals("Aryan", AuthUserObj.getUsername());
		assertEquals("Password", AuthUserObj.getPassword());
		assertEquals("DemoAuthToken", AuthUserObj.getAuthToken());
		assertEquals("Employee", AuthUserObj.getRole());

	}

	/**
	 * AppUser Constructor Negative Test Case
	 */
	@Test
	void AppUserConstructorNegativeTest() {
		AppUser Obj = new AppUser();
		assertFalse(Obj.getPassword() == "Passowrd");
		assertFalse(Obj.getRole() == "Employee");
		assertFalse(Obj.getAuthToken() == "DemoAuthToken");
		assertFalse(Obj.getUserid() == "DemoUserId");
		assertFalse(Obj.getUsername() == "Aryan");

	}

	/**
	 * Set Amount Test Case
	 */
	@Test
	void setAmountTest() {
		AppUserObj.setAuthToken("DemoToken");
		assertEquals("DemoToken", AppUserObj.getAuthToken());
	}

	/**
	 * Set Amount Negative Test Case
	 */
	@Test
	void setAmountNegativeTest() {
		AppUserObj.setAuthToken("DemoToken");
		assertNotEquals("NotDemoToken", AppUserObj.getAuthToken());
	}

	/**
	 * Get Account Id Test Case
	 */
	@Test
	void getAccountIdTest() {
		AppUserObj.setAuthToken("DemoToken");
		assertTrue(AppUserObj.getAuthToken() == "DemoToken");

	}

	/**
	 * Get Account Negative Test Case
	 */
	@Test
	void getAccountIdNegativeTest() {
		AppUserObj.setAuthToken("DemoToken");
		assertFalse(AppUserObj.getAuthToken() == "NotDemoToken");
	}

	/**
	 * Set Role Test Case
	 */
	@Test
	void setRoleTest() {
		AppUserObj.setRole("DemoRole");
		assertEquals("DemoRole", AppUserObj.getRole());
	}

	/**
	 * Set Role Negative Test Case
	 */
	@Test
	void setRoleNegativeTest() {
		AppUserObj.setRole("DemoRole");
		assertNotEquals("NotDemoRole", AppUserObj.getRole());
	}

	/**
	 * Get Account Test Case
	 */
	@Test
	void getAccountTest() {
		AppUserObj.setRole("DemoRole");
		assertTrue(AppUserObj.getRole() == "DemoRole");
	}

	/**
	 * Get Account Negative Test Case
	 */
	@Test
	void getAccountNegativeTest() {
		AppUserObj.setRole("DemoRole");
		assertFalse(AppUserObj.getRole() == "NotDemoRole");
	}

	/**
	 * Set Name Test Case
	 */
	@Test
	void setNameTest() {
		AppUserObj.setUsername("DemoName");
		assertEquals("DemoName", AppUserObj.getUsername());
	}

	/**
	 * Set Name Negative Test Case
	 */
	@Test
	void setnameNegativeTest() {
		AppUserObj.setUsername("DemoName");
		assertNotEquals("NotDemoName", AppUserObj.getUsername());
	}

	/**
	 * Get Account Name Test Case
	 */
	@Test
	void getAccountname() {
		AppUserObj.setUsername("DemoName");
		assertTrue(AppUserObj.getUsername() == "DemoName");
	}

	/**
	 * Set Account Name Test Case
	 */
	@Test
	void setAccountnameTest() {
		AppUserObj.setUsername("DemoName");
		assertTrue(AppUserObj.getUsername() == "DemoName");
	}

	/**
	 * Set Account Name Negative
	 */
	@Test
	void setAccountnameNegativeTest() {
		AppUserObj.setUsername("DemoName");
		assertFalse(AppUserObj.getUsername() == "NotDemoName");
	}

	/**
	 * Get Account Negative Test
	 */
	@Test
	void getAccounnameNegativeTest() {
		AppUserObj.setUsername("DemoName");
		assertFalse(AppUserObj.getUsername() == "NotDemoName");
	}

}
