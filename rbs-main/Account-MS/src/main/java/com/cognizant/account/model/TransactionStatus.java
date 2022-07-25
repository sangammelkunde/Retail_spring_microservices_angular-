package com.cognizant.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class that is sent as a response for the transaction requests. 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionStatus {

	private String message;
	private double initialBalance;
	private double finalBalance;

}
