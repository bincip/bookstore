package com.bookstore.web.service;

import java.util.List;

import com.bookstore.web.entity.Book;

public interface CartService {

	float getPayableAmount(List<Book> books, String couponCode);
}
