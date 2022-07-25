package com.cognizant.transactionservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 *         This is Pojo Class for transaction status
 *
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
