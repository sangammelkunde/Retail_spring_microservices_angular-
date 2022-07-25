package com.cognizant.RetailBanking.Customers.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class CustomerDetailsResponse {

	
	private String userid;
	
	private String username;
	
	private String password;
	
	private Date dateOfBirth;
	
	private String pan_no;
	
	private String address;
	
	private List<Account> accounts = new ArrayList<>();
}
