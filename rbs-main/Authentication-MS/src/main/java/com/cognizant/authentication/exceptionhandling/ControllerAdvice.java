package com.cognizant.authentication.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.authentication.errorhandling.ErrorMessage;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Controller Advice class for handling Http Exceptions
 * 
 */
@RestControllerAdvice
public class ControllerAdvice {

	String NotAuthorized = "${ControllerAdvice.NotAuthorized}";

	/**
	 * Exception Method for APPUSER not found
	 * 
	 * @param userNotFoundException
	 * @return Error Message
	 * 
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFoundException(UsernameNotFoundException userNotFoundException) {
		return new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), userNotFoundException.getMessage());
	}

	/**
	 * Exception for jwt malfunctioned error
	 * 
	 * @return Error Message
	 */
	@ExceptionHandler(MalformedJwtException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorMessage tokenMalformedException() {
		return new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), NotAuthorized);
	}

	/**
	 * Exception for JWT Signature unauthorized error
	 * 
	 * @return Error Message
	 */
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorMessage tokenSignatureException() {
		return new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), NotAuthorized);
	}

}