package com.saathisquare.authservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saathisquare.authservice.util.Constants;
import com.saathisquare.authservice.util.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneral(Exception ex) {
		LOGGER.info("Inside : handleGeneral : {}",ex);
		return ResponseEntity.status(500)
				.body(new Response<>(Constants.GLOBAL_ERROR_STATUS_CODE, Constants.GLOBAL_ERROR_MESSAGE, null));
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new Response<>(Constants.VALIDATION_ERROR_API_CODE, ex.getMessage(), null));
	}

}
