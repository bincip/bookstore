package com.bookstore.web.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String fieldName, String fieldValue) {
		super("Unable to find Book with " + fieldName + ": " + fieldValue);
	}
	
	public BookNotFoundException(String message) {
		super(message);
	}

}
