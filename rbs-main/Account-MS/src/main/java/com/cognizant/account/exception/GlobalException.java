package com.cognizant.account.exception;

import java.net.ConnectException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.account.model.AccountErrorResponse;

import feign.FeignException;

/*
 * Rest Controller Advice for handling all exceptions for the controller.
 */

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
	
	/*
     * Global exception controller for Account Not Found Error.
     */
	
	@ExceptionHandler({AccountNotFoundException.class})
	public ResponseEntity<AccountErrorResponse> handleAccountNotFound(AccountNotFoundException ex){
		AccountErrorResponse accErr = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage(), "Account Not Found.");
		return new ResponseEntity<AccountErrorResponse>(accErr, HttpStatus.BAD_REQUEST);
	}
	
	/*
     * Global exception controller for Access Denied Error.
     */
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<AccountErrorResponse> handleAccessDenied(AccessDeniedException ex){
		AccountErrorResponse accErr = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN, ex.getMessage(), "Access Denied.");
		return new ResponseEntity<AccountErrorResponse>(accErr, HttpStatus.FORBIDDEN);
	}
	
	/*
     * Global exception controller for Minimum Balance Error.
     */
	@ExceptionHandler({MinimumBalanceException.class})
	public ResponseEntity<AccountErrorResponse> handleMinimumBalance(MinimumBalanceException ex){
		AccountErrorResponse accErr = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "Minimum Balance Not Maintained.");
		return new ResponseEntity<AccountErrorResponse>(accErr, HttpStatus.NOT_ACCEPTABLE);
	}
	
	/*
     * Global exception controller for Server Error.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ FeignException.class })
    public ResponseEntity<AccountErrorResponse> handleFeignException(FeignException ex) {
        AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now() ,HttpStatus.INTERNAL_SERVER_ERROR ,ex.getMessage() ,"Server Down Try Later." );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /*
     * Global exception controller for Connection Error.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ ConnectException.class })
    public ResponseEntity<AccountErrorResponse> handleConnectException(ConnectException ex) {
        AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now() ,HttpStatus.INTERNAL_SERVER_ERROR ,ex.getMessage() ,"Connection Error." );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /*
     * Global exception controller for General Exceptions.
     */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<AccountErrorResponse> handleException(Exception ex){
		AccountErrorResponse accErr = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "Exception.");
		return new ResponseEntity<AccountErrorResponse>(accErr, HttpStatus.NOT_ACCEPTABLE);
	}

}
