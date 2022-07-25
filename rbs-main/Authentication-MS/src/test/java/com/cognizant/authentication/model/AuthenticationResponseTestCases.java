package com.cognizant.authentication.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Authentication Response Test class
 * 
 * @author pod-4
 *
 */
public class AuthenticationResponseTestCases {

	/**
	 * AuthenticationResponse Object
	 */
	AuthenticationResponse response = new AuthenticationResponse();

	/**
	 * Constructor Testcase
	 */
	@Test
	void ConstructorTest() {
		AuthenticationResponse AuthObj = new AuthenticationResponse("UserId007", "Aryan", true);
		assertEquals("UserId007", AuthObj.getUserid());
		assertEquals("Aryan", AuthObj.getName());
		assertEquals(true, AuthObj.isValid());
	}

	/**
	 * Constructor Negative Testcase
	 */
	@Test
	void ConstructorNegativeTestcase() {
		AuthenticationResponse Obj = new AuthenticationResponse();
		assertFalse(Obj.getName() == "Aryan");
		assertFalse(Obj.getUserid() == "UserId007");
		assertFalse(Obj.isValid() == true);
	}

	/**
	 * set User ID Test case
	 */
	@Test
	void setUserIdTest() {
		response.setUserid("DemoCustomer");
		assertEquals("DemoCustomer", response.getUserid());
	}

	/**
	 * set user Id negative Test case
	 */
	@Test
	void setUserIdNegagtiveTest() {
		response.setUserid("DemoCustomer");
		assertNotEquals("NotDemoCustomer", response.getUserid());
	}

	/**
	 * get User id Test case
	 */
	@Test
	void getUserIdTest() {
		response.setUserid("DemoCustomer");
		assertTrue(response.getUserid() == "DemoCustomer");
	}

	/**
	 * get User id Negative Test case
	 */
	@Test
	void getUserIdNegativeTest() {
		response.setUserid("DemoCustomer");
		assertFalse(response.getUserid() == "NotDemoCustomer");
	}

	/**
	 * set Name Testcase
	 */
	@Test
	void setNameTest() {
		response.setName("Likhith");
		assertEquals("Likhith", response.getName());
	}

	/**
	 * Set Name Negative Testcase
	 */
	@Test
	void setNameNegativeTest() {
		response.setName("Likhith");
		assertNotEquals("NotLikhith", response.getName());
	}

	/**
	 * Get Name testcase
	 */
	@Test
	void getNameTest() {
		response.setName("Samuel");
		assertTrue(response.getName() == "Samuel");
	}

	/**
	 * Get name Negative Testcase
	 */
	@Test
	void getNameNegativeTest() {
		response.setName("Samuel");
		assertFalse(response.getName() == "NotSamuel");
	}

	/**
	 * Set isValid Test case
	 */
	@Test
	void setIsValidTest() {
		response.setValid(true);
		assertEquals(true, response.isValid());
	}

	/**
	 * Set isValid Negative Test case
	 */
	@Test
	void setIsValidNegativeTest() {
		response.setValid(true);
		assertNotEquals(false, response.isValid());
	}

	/**
	 * AuthenticationResponse Object
	 */
	AuthenticationResponse response1 = new AuthenticationResponse();

	/**
	 * Authentication Response toString Testcase
	 */
	@Test
	void toSringTest() {
		assertEquals(response1.toString(), response.toString());
	}

	/**
	 * Get Password Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterPasswordTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		field.set(pojo, "password");
		// when
		String result = pojo.getPassword();
		// then
		assertEquals("password", result, "password");
	}

	/**
	 * Set Id Test case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterIdTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUserid("abcd");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("userid");
		field.setAccessible(true);
		assertEquals("abcd", field.get(pojo), "emp");
	}

	/**
	 * Get Id Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterIdTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("userid");
		field.setAccessible(true);
		field.set(pojo, "values");
		// when
		String result = pojo.getUserid();
		// then
		assertEquals("values", result, "magic_values");
	}

	/* Negative Test Cases */

	/**
	 * Get Password Negative Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterPasswordNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		field.set(pojo, "magic_value");
		// when
		String result = pojo.getPassword();
		// then
		assertNotEquals("field wasn't retrieved properly", result, "magic_values");
	}

	/**
	 * Set Id Negative Test case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterIdNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUserid("abcd");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("userid");
		field.setAccessible(true);
		assertNotEquals("Fields didn't match", field.get(pojo), "emp");
	}

	/**
	 * Get Id Negative Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterIdNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("userid");
		field.setAccessible(true);
		field.set(pojo, "values");
		// when
		String result = pojo.getUserid();
		// then
		assertNotEquals("field wasn't retrieved properly", result, "magic_values");
	}

}
