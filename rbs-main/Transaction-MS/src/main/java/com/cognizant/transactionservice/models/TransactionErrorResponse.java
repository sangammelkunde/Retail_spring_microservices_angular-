package com.cognizant.transactionservice.models;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 *         This is Pojo Class for transaction error responses
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionErrorResponse {

	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;
}
