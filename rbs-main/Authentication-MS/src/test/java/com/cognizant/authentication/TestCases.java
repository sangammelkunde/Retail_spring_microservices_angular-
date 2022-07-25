package com.cognizant.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.authentication.model.AppUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Authentication Application Test Cases
 * 
 * @author Pod-4
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCases {

	public String token;

	/**
	 * MockMvc Variable
	 */
	private MockMvc mockMvc;

	/**
	 * Autowired WebApplicationContext class
	 */
	@Autowired
	private WebApplicationContext wc;

	/**
	 * Employees List
	 */
	List<AppUser> employees = new ArrayList<AppUser>();
	static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Set Up Method to get called before each method of the class
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
		loginTest();
	}

	/**
	 * ParseResponse method to parse Response
	 * 
	 * @param <T>
	 * @param result
	 * @param responseClass
	 * @return mapper
	 * @throws UnsupportedEncodingException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static <T> T parseResponse(MvcResult result, Class<T> responseClass)
			throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		String contentAsString = result.getResponse().getContentAsString();
		return mapper.readValue(contentAsString, responseClass);
	}

	/**
	 * Login Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void loginTest() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("EMP101", "Peter", "pass", "", "Employee");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/login").content(json).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.jsonPath("$.authToken").exists())
				.andReturn();
		AppUser response = parseResponse(andReturn, AppUser.class);
		token = response.getAuthToken();
	}

	/**
	 * Get Validate Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getValidateTest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validateToken").header("Authorization", "Bearer " + token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/**
	 * Get Role Test Case
	 * 
	 * @throws Exception
	 */
	@Test
	public void getRoleTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/role").accept(MediaType.APPLICATION_JSON)).andReturn();
	}

	/**
	 * Save employee Mapping Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void saveEmployeeTest() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("899", "DemoName", "DemoPassword", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userid").exists());
	}

	/**
	 * Get Health Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getHealthTest() throws JsonProcessingException, Exception {
		System.err.println(token);
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		boolean equals = andReturn.getResponse().getContentAsString().equals("Status>>>>>>UP");
		assertEquals(equals, true);

	}

	/**
	 * Set Name Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterNameTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUsername("abc");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(pojo), "abc");
	}

	/**
	 * Get Name Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterNameTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		field.set(pojo, "magic_values");
		// when
		String result = pojo.getUsername();
		// then
		assertEquals("field wasn't retrieved properly", result, "magic_values");
	}

	/**
	 * Set Password Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterPasswordTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setPassword("aaabbb");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(pojo), "aaabbb");
	}

	// Negative Test Cases

	/**
	 * Set Password Negative Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterPasswordNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setPassword("abcde");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		assertNotEquals("Fields didn't match", field.get(pojo), "abc");
	}

	/**
	 * Set Name Negative Test Case
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void setterNameNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUsername("abcd");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		assertNotEquals("Fields didn't match", field.get(pojo), "abc");
	}

	/**
	 * Get Name Negative TestCase
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void getterNameNegativeTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		field.set(pojo, "values");
		// when
		String result = pojo.getUsername();
		// then
		assertNotEquals("field wasn't retrieved properly", result, "magic_values");
	}

	/**
	 * Login Negative Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void loginNegativeTest() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("EMP101", "Peter", "pass", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/login").content(json).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.authToken2").doesNotExist()).andReturn();
	}

	/**
	 * Save Employee Negative Test
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void saveEmployeeNegativeTest() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("2323", "DemoName", "DemoPassword", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userid1").doesNotExist());

	}

	/**
	 * Get Not Valid test case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getNotValidateTest() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.get("/validateToken").header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

	/**
	 * Get One Employee Negative Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getOneEmployeesNegativeTestCase() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.post("/find").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	/**
	 * Get One Employee Negative Test Case with method not allowed scenario
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getOneEmployeesTest() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.post("/find").header("Authorization", "Bearer " + token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	/**
	 * Get Health Negative Test Case
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void getHealthNegativeTest() throws JsonProcessingException, Exception {
		System.err.println(token);
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		boolean equals = andReturn.getResponse().getContentAsString().equals("DOWN");
		assertNotEquals(equals, true);

	}

}
