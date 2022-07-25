package com.cognizant.RetailBanking.Customers.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AccountCreationStatus {

	@Id
	long accountId;
	String message;
	

	
}
