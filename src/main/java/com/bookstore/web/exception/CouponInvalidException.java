package com.bookstore.web.exception;

public class CouponInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CouponInvalidException(String fieldName, String fieldValue) {
		super("Coupon:" + fieldValue + " is invalid or Expired");
	}
	
	public CouponInvalidException(String message) {
		super(message);
	}

}
