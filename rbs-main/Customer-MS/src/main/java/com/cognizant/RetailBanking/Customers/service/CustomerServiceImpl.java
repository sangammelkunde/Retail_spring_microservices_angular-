package com.cognizant.RetailBanking.Customers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.RetailBanking.Customers.Exception.AccessDeniedException;
import com.cognizant.RetailBanking.Customers.Exception.ConsumerAlreadyExistException;
import com.cognizant.RetailBanking.Customers.Exception.ServiceFailException;
import com.cognizant.RetailBanking.Customers.Repository.CustomerRepository;
import com.cognizant.RetailBanking.Customers.feign.AccountFeign;
import com.cognizant.RetailBanking.Customers.feign.AuthorizationFeign;
import com.cognizant.RetailBanking.Customers.feign.RulesFeign;
import com.cognizant.RetailBanking.Customers.model.Account;
import com.cognizant.RetailBanking.Customers.model.AccountCreationStatus;
import com.cognizant.RetailBanking.Customers.model.AccountInfo;
import com.cognizant.RetailBanking.Customers.model.AppUser;
import com.cognizant.RetailBanking.Customers.model.AuthenticationResponse;
import com.cognizant.RetailBanking.Customers.model.CustomerEntity;
import com.cognizant.RetailBanking.Customers.model.TransactionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final String CUSTOMER = "CUSTOMER";
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	AuthorizationFeign authorizationFeign;
	
	@Autowired
	AccountFeign accountFeign;
	
	@Autowired
	RulesFeign rulesFeign;
	
	
	/**
	 * @Create customer
	 * @param userid
	 * @param username
	 * @param password
	 * @param DOB
	 * @param address
	 */
	@Override
	public AccountCreationStatus createCustomer(String token, CustomerEntity customer) {
		log.info("Start Create Customer ");
		CustomerEntity checkCustomerExists = getCustomerDetail(token, customer.getUserid());
		if(checkCustomerExists!=null)
		{
			throw new ConsumerAlreadyExistException("Customer already exist.");
		}
		else {
			AppUser user = new AppUser(customer.getUserid(), customer.getUsername(), customer.getPassword(), null,
					CUSTOMER);
			authorizationFeign.createUser(user);
		}
		
		Account acc = new Account();
		acc.setCustomerId(customer.getUserid());
		acc.setAccountType("Savings");
		acc.setOwnerName(customer.getUsername());
		acc.setBalance(0);
		AccountCreationStatus status = accountFeign.createAccount(token,customer.getUserid(),acc);
		
		
		customerRepo.save(customer);
		log.info("Customer details saved.");
		

		return status;
	}

	
	/**
	 * @GET CUSTOMER DETAILS 
	 * @CUSTOMER ENTITY
	 */

	@Override
	public CustomerEntity getCustomerDetail(String token, String id) {
		log.info("Start Get Customer");
		Optional<CustomerEntity> customer = customerRepo.findById(id);
		if (!customer.isPresent())
			return null;
		log.info("Consumer details fetched.");
		return customer.get();
	}

	/**
	 * @UPDATE CUSTOMER FROM CUSTOMER ENTITY
	 * @PARAM CUSTOMER ENTITY
	 */

	@Override
	public CustomerEntity updateCustomer(String token, CustomerEntity customer) {
		log.info("Update Customer Started");
		return customerRepo.save(customer);
	}
		
	
	/**
	 * @DELETE CUSTOMER FROM DB
	 * @PARAM USERID
	 */
	@Override
	public boolean deleteCustomer(String token, String id) {
		log.info("Start  Delete Customer ");
		CustomerEntity customer = customerRepo.findById(id).get();
		if (customer != null) {
			customerRepo.deleteById(id);
			accountFeign.deleteAccountsByCustomerId(token, id);
		}
		else
			return false;
		log.info("Consumer details deleted.");
		return true;
	}
	/**
	 * @HAS PERMISSION FOR AUTHENTICATION
	 * @PARAM TOKEN
	 */
	@Override
	public AuthenticationResponse hasPermission(String token) {
		return authorizationFeign.getValidity(token);
	}

	
	@Override
	public AuthenticationResponse hasEmployeePermission(String token) {
		AuthenticationResponse validity = authorizationFeign.getValidity(token);
//		if (!authorizationFeign.getRole(validity.getUserid()).equalsIgnoreCase("EMPLOYEE"))
//			throw new AccessDeniedException("NOT ALLOWED");
//		else
			return validity;
	}
	
	@Override
	public AuthenticationResponse hasCustomerPermission(String token) {
		AuthenticationResponse validity = authorizationFeign.getValidity(token);
//		if (!authorizationFeign.getRole(validity.getUserid()).equalsIgnoreCase(CUSTOMER))
//			throw new AccessDeniedException("NOT ALLOWED");
//		else
			return validity;
	}

	
	/**
	 * @FALL BACK (IF LOSS IN CONNECTION OR SERVER IS DOWN)
	 * @OUTPUT : LOGINPAGE
	 */
	
	public CustomerEntity welcomeFallBack(String token, String id) throws ServiceFailException
	{
		throw new ServiceFailException("server down");
	}

/////////////////////////////////////////////////////////////////////////////////////	
	
	
	/**
	 * @SAVE CUSTOMER
	 * @CUSTOMER ENTITY(CUSTOMER DETAIL)
	 * @TOKEN
	 * 
	 */
	@Override
	public CustomerEntity saveCustomer(String token, CustomerEntity customer) {
		log.info("Start Save Customer");
	CustomerEntity checkCustomerExists = getCustomerDetail(token,customer.getUserid());
	if(checkCustomerExists ==  null) {
		AppUser user = new AppUser(customer.getUserid(), customer.getUsername(), customer.getPassword(),null,CUSTOMER);
		authorizationFeign.createUser(user);
	}
	log.info("Customer Saved");
		return customerRepo.save(customer);
		
	}
	
	
	/*
	 * @DEDUCTION CHARGE FOR INDIVIDUAL TRANSACTION
	 * @MIN BALANCE
	 * @ACCOUNT FEIGN
	 * 
	 */
	
	public int deductServiceCharges(String token) {
		List<Account> accList = accountFeign.getAllAccounts(token);
		List<Account> minList = new ArrayList<>();
		for(Account acc : accList) {
			if(acc.getBalance() < 500.0) {
				minList.add(acc);
			}
		}
		
		for(Account acc : minList) {
			double balance = acc.getBalance();
			double charge = rulesFeign.getServiceCharge(balance);
			
			AccountInfo accInfo = new AccountInfo(acc.getAccountId(), charge);
			
			accountFeign.deduct(token, accInfo);
		}
		return minList.size();
	}
	
}


	

