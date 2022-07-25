package com.cognizant.transactionservice.exception;

import java.net.ConnectException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.transactionservice.models.TransactionErrorResponse;
import feign.FeignException;

/**
 * 
 *         Global Exception Handler Class Implementation
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<TransactionErrorResponse> nullPointer(MinimumBalanceException exception, WebRequest request) {
		TransactionErrorResponse response = new TransactionErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Minimun Balance Should Be Maintained.");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<TransactionErrorResponse> insufficientBalance(InsufficientBalanceException exception, WebRequest request) {
		TransactionErrorResponse response = new TransactionErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Insufficient Balance.");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ AccountNotFoundException.class })
	public ResponseEntity<TransactionErrorResponse> handleAccountNotFound(AccountNotFoundException ex) {
		TransactionErrorResponse accErr = new TransactionErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST,
				ex.getMessage(), "Account Not Found.");
		return new ResponseEntity<TransactionErrorResponse>(accErr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<TransactionErrorResponse> handleAccessDenied(AccessDeniedException ex) {
		TransactionErrorResponse accErr = new TransactionErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN,
				ex.getMessage(), "Access Denied.");
		return new ResponseEntity<TransactionErrorResponse>(accErr, HttpStatus.FORBIDDEN);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ FeignException.class })
	public ResponseEntity<TransactionErrorResponse> handleFeignException(FeignException ex) {
		TransactionErrorResponse response = new TransactionErrorResponse(LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Server Down Try Later...");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ ConnectException.class })
	public ResponseEntity<TransactionErrorResponse> handleConnectException(ConnectException ex) {
		TransactionErrorResponse response = new TransactionErrorResponse(LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Connection Error...");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}