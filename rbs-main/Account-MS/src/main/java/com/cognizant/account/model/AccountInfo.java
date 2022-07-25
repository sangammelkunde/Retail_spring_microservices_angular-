package com.cognizant.account.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class used to send input data for deposit, withdraw and deduct requests.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccountInfo {
	
	private long accountId;
	
	private double balance;
	


}
