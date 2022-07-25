package com.cognizant.transactionservice.models;

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

	private double balance;

	private String accountType;

	private String ownerName;

}