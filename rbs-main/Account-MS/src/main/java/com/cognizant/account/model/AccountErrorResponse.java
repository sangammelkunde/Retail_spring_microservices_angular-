package com.cognizant.account.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The class used to format error response.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccountErrorResponse {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;

}
