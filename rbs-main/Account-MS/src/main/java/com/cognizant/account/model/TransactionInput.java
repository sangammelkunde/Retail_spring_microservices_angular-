package com.cognizant.account.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class used to send input data for the transfer request.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInput {

	private long sourceAccount;

	private long targetAccount;

	@Positive(message = "Transfer amount cannot be negative")
	@Min(value = 1, message = "Amount must be greater than 1")
	private double amount;

	private String typeOfTrasaction;

}