package com.cognizant.CustomerService.controller;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.RetailBanking.Customers.Application;
import com.cognizant.RetailBanking.Customers.Controller.CustomerController;
import com.cognizant.RetailBanking.Customers.Exception.ConsumerAlreadyExistException;
import com.cognizant.RetailBanking.Customers.model.AppUser;
import com.cognizant.RetailBanking.Customers.model.CustomerEntity;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class })
 public class CustomerTests {

	public String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFbXBsb3llZSIsImV4cCI6MTYxNjY5MzEwNCwiaWF0IjoxNjE2NjkxMzA0fQ._OoNumEfxD790xZXT33LvbGEbEhEmOWMoWO8ADLuzZg";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;
	
	@Autowired
	CustomerController controller;

	List<AppUser> employees = new ArrayList<AppUser>();
	static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Before
	public void setUp() throws JsonProcessingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	/**
	 * @SAVE CUSTOMER TEST CASE
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void saveCustomer() throws JsonProcessingException, Exception {
		System.err.println(token);
		CustomerEntity menu = null;
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/createCustomer").header("Authorization", "Bearer " + token)
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400)).andReturn();

	}
	
	
	/**
	 * @SAVE MULTIPLE CUSTOMER ONE BY ONE FROM DIFFERENT SERVERS
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void saveCustomers() throws JsonProcessingException, Exception {
		System.err.println(token);
		CustomerEntity menu = new CustomerEntity();
		menu.setAddress("Chennai");
		menu.setDateOfBirth(new Date(20));
		menu.setPan_no("asdfghjklz");
		menu.setPassword("barat");
		menu.setUsername("barat");
		menu.setUserid("1234");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/saveCustomer").header("Authorization", "Bearer " + token)
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200)).andReturn();

	}
	
	/**
	 * @UPDATE CUSTOMER INFORMATION 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	
	@Test
	public void updateCustomers() throws JsonProcessingException, Exception {
		System.err.println(token);
		CustomerEntity menu = new CustomerEntity();
		menu.setAddress("Chennai");
		menu.setDateOfBirth(new Date(20));
		menu.setPan_no("asdfghjklz");
		menu.setPassword("barat");
		menu.setUsername("barat");
		menu.setUserid("CUSTOMER101");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/updateCustomer/CUSTOMER101").header("Authorization", "Bearer " + token)
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200)).andReturn();

	}

	
	/**
	 * @UNSUCCESSFUL CUSTOMER CREATION
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void unsuccesfulCustomer() throws JsonProcessingException, Exception {
		System.err.println(token);
		CustomerEntity menu = new CustomerEntity();
		menu.setAddress("Chennai");
		menu.setDateOfBirth(new Date(60));
		menu.setPan_no("asdfghjklz");
		menu.setPassword("barat");
		menu.setUsername("barat");
		menu.setUserid("1234");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/createCustomer").header("Authorization", "Bearer " + token)
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(406)).andReturn();
	}

	
/**
 * @GET EMPLOYEE
 * @throws JsonProcessingException
 * @throws Exception
 */
	@Test
	public void getOneEmployees() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.get("/getCustomerDetails/CUSTOMER101")
				.header("Authorization", "Bearer " + token).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));

	}

	
	/**
	 * @DELETE EMPLOYEE
	 * @throws Exception
	 */
	@Test
	public void deleteEmployeeAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCustomer/CUSTOMER101", 1).header("Authorization",
				"Bearer " + token)).andExpect(status().is(200));
	}

	
	
	@Test
	public void deleteNotPresentEmployeeAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCustomer/CUSTOMER1012", 1).header("Authorization",
				"Bearer " + token)).andExpect(status().is(406));
	}

	
	/**
	 * @CUSTOMER ALREADY EXISTS
	 */
	@Test
	public void customerAlreadyExist() {
		ConsumerAlreadyExistException e1 = new ConsumerAlreadyExistException("hi");
		ConsumerAlreadyExistException e2 = new ConsumerAlreadyExistException("hi");
		assertThat(e1).isNotEqualTo(e2);
	}

	
}