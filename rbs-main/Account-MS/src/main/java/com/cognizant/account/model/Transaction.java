package com.cognizant.account.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/*
 * The model class of the transaction microservice.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {

	private long id;

	private long sourceAccountId;

	private String sourceOwnerName;

	private long targetAccountId;

	private String targetOwnerName;

	private double amount;

	private LocalDate dateOfTransaction;

	private String typeOfTransaction;

}