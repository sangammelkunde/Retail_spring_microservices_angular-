package com.cognizant.RetailBanking.Customers.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Account {

	
	private long accountId;
	
	private String customerId;
	
	private String  ownerName;
	
	private String accountType;
	
	private double balance;
	
	

	
}
