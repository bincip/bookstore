package com.bookstore.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.web.entity.Book;
import com.bookstore.web.entity.Coupon;
import com.bookstore.web.exception.BookNotFoundException;
import com.bookstore.web.exception.CouponInvalidException;
import com.bookstore.web.repository.BookRepository;
import com.bookstore.web.repository.CouponRepository;
import com.bookstore.web.service.CartService;
import com.bookstore.web.util.DateHelper;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CouponRepository couponRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	DateHelper dateHelper;

	@Override
	public float getPayableAmount(List<Book> books, String couponCode) {

		Coupon coupon = null;

		if (couponCode != null && !couponCode.isEmpty()) {

			coupon = couponRepository.findByCodeAndExpiryDateAfter(couponCode, dateHelper.getCurrentTime())
					.orElseThrow(() -> new CouponInvalidException("Code", String.valueOf(couponCode)));
		}

		float totalAmount = 0;
		for (Book book : books) {
			String isbn = book.getISBN();

			book = bookRepository.findByISBN(isbn)
					.orElseThrow(() -> new BookNotFoundException("ISBN", String.valueOf(isbn)));

			float itemprice = book.getPrice();

			if (couponApplicable(book, coupon)) {
				float discountAmount = (itemprice * coupon.getDiscountPercentage()) / 100;
				itemprice = itemprice - discountAmount;
			}

			totalAmount = totalAmount + itemprice;
		}
		return totalAmount;
	}

	private boolean couponApplicable(Book book, Coupon coupon) {
		return (coupon != null && coupon.getBookCategories().stream()
				.anyMatch(category -> (category.getId().equals(book.getBookCategory().getId())))) ;
		
	}

}
