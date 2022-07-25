package com.cognizant.account.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The main model class for the account microservice.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;

	@NotBlank(message = "{customerId.required}")
	private String customerId;

	@NotNull(message = "{balance.required}")
	private double balance;

	@NotBlank(message = "{accountType.required}")
	private String accountType;

	@NotBlank(message = "{ownerName.required}")
	private String ownerName;

}
