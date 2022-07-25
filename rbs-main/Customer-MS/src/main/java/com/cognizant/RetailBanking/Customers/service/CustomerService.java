package com.cognizant.RetailBanking.Customers.service;

import com.cognizant.RetailBanking.Customers.model.AccountCreationStatus;
import com.cognizant.RetailBanking.Customers.model.AuthenticationResponse;
import com.cognizant.RetailBanking.Customers.model.CustomerEntity;

public interface CustomerService {

	public AccountCreationStatus createCustomer(String token, CustomerEntity customer);
	
	public CustomerEntity getCustomerDetail(String token, String id);
	
	public CustomerEntity saveCustomer(String token, CustomerEntity customer);
	
	public CustomerEntity updateCustomer(String token,CustomerEntity customer);
	
	public boolean deleteCustomer(String token, String id);
	
	public AuthenticationResponse hasEmployeePermission(String token);
	
	AuthenticationResponse hasCustomerPermission(String token);

	public AuthenticationResponse hasPermission(String token);
	
	public int deductServiceCharges(String token);
	
}
