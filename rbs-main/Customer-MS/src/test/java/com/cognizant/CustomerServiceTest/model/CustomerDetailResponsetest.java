package com.cognizant.CustomerServiceTest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.RetailBanking.Customers.model.CustomerDetailsResponse;


class CustomerDetailResponsetest {

	CustomerDetailsResponse customer = new CustomerDetailsResponse();
	CustomerDetailsResponse customer2 = new CustomerDetailsResponse("111","pawan","gho",new Date(0),"123","chn",null);

	@Test
	void setUserIdTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("pawan");
		assertEquals("pawan", customer.getUsername());
	}

	@Test
	void setPasswordTest() {
		customer.setPassword("abc");
		assertEquals("abc", customer.getPassword());
	}

	@Test
	void setAddressTest() {
		customer.setAddress("abc");
		assertEquals("abc", customer.getAddress());
	}

	@Test
	void setPanTest() {
		customer.setPan_no("asdfghjklz");
		assertEquals("asdfghjklz", customer.getPan_no());
	}

	@Test
	void setDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

	@Test
	void getAccTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void getUserNameTest() {
		customer.setUsername("pawan");
		assertEquals("pawan", customer.getUsername());
	}

	@Test
	void getPasswordTest() {
		customer.setPassword("abc");
		assertEquals("abc", customer.getPassword());
	}

	@Test
	void getAddressTest() {
		customer.setAddress("abc");
		assertEquals("abc", customer.getAddress());
	}

	@Test
	void getPanTest() {
		customer.setPan_no("asdfghjklz");
		assertEquals("asdfghjklz", customer.getPan_no());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

}
