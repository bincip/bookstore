package com.bookstore.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstore.web.entity.Book;
import com.bookstore.web.entity.BookCategory;
import com.bookstore.web.service.CartService;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

	@InjectMocks
	CartController cartController;

	@Mock
	CartService cartService;

	@Test
	public void getPayableAmount() {
		
		List<Book> book = new ArrayList<Book>();
		
		book.add(getFictionBookOne());
		book.add(getFictionBookTwo());

		when(cartService.getPayableAmount(book, "")).thenReturn((float) 270);
		float amount = cartController.getPayableAmount(book, "");
		assertNotNull(amount);
		assertThat(amount).isGreaterThan(0);
	}
	
	private Book getFictionBookOne() {

		Book book = new Book();
		book.setId((long) 1);
		book.setAuthor("Gabriel García Marquez");
		book.setName("One Hundred Years of Solitude");
		book.setISBN("978-3-16-148410-4");
		book.setPrice(130);
		book.setBookCategory(getFictionCategory());

		return book;
	}

	private Book getFictionBookTwo() {

		Book book = new Book();
		book.setAuthor("Paulo Coelho");
		book.setName("The Alchemist");
		book.setISBN("978-3-16-148410-6");
		book.setPrice(140);
		book.setBookCategory(getFictionCategory());

		return book;
	}
	
	
	private BookCategory getFictionCategory() {
		BookCategory category = new BookCategory();
		category.setId((long) 1);
		category.setName("Fiction");
		return category;
	}
	
}
