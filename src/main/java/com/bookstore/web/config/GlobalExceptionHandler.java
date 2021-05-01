package com.bookstore.web.config;

/**
 * 
 * Exception handler
 * 
 * @author Bincy P
 *
 */

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bookstore.web.dto.ErrorDto;
import com.bookstore.web.exception.BookNotFoundException;
import com.bookstore.web.exception.CouponInvalidException;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ BookNotFoundException.class })
	public @ResponseBody ErrorDto handleBookNotFoundException(BookNotFoundException e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ CouponInvalidException.class })
	public @ResponseBody ErrorDto handleCouponInvalidException(CouponInvalidException e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ Exception.class })
	public @ResponseBody ErrorDto handleException(Exception e) {
		String errorlogId = UUID.randomUUID().toString();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setErrorlogId(errorlogId);

		return errorDto;
	}
	
	

}
