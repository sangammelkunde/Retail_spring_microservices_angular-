package com.cognizant.RetailBanking.Customers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionStatus {
	
	private String message;
	private double initialBalance;
	private double finalBalance;
	
}
