package com.bookstore.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.web.entity.Book;
import com.bookstore.web.service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Shopping Cart API" })
@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@ApiOperation(value = "Gets the payable amount for the books selected after applying the coupon", 
			notes = "ISBN of the selected book is mandatory ", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully calculated the payable amount"),
			@ApiResponse(code = 404, message = "Not a valid coupon code"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@PostMapping("/get-total")
	public float getPayableAmount(@RequestBody List<Book> books, @RequestParam(required = false) String couponCode) {
		return cartService.getPayableAmount(books, couponCode);
	}
}
