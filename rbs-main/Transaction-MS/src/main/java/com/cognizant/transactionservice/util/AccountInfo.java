package com.cognizant.transactionservice.util;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 *         This is Pojo Class for Account Information
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

	@NotNull(message = "Please enter account Id")
	private long accountId;
	@NotNull(message = "Please enter amount")
	private double balance;

}