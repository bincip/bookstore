package com.bookstore.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstore.web.dto.BookCategoryDto;
import com.bookstore.web.dto.BookDto;
import com.bookstore.web.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	private BookService bookService;

	@Test
	public void findByIdTest() {

		when(bookService.getBook((long) 1)).thenReturn(getBook());
		BookDto response = bookController.findById((long) 1);
		assertNotNull(response);
		assertEquals("978-3-16-148410-4", response.getISBN());

	}

	@Test
	public void createBookTest() {

		BookDto secondBook = getSecondBook();

		BookDto secondBookAfterSave = secondBook;
		secondBookAfterSave.setId((long) 1);

		when(bookService.createBook(secondBook)).thenReturn(secondBookAfterSave);
		BookDto response = bookController.createBook(secondBook);
		assertNotNull(response);
		assertEquals( "978-3-16-148410-6", response.getISBN());

	}

	@Test
	public void updateBookTest() {

		BookDto secondBook = getSecondBook();

		BookDto secondBookAfterUpdate = secondBook;
		secondBookAfterUpdate.setPrice(150);

		when(bookService.updateBook(secondBook, 1)).thenReturn(secondBookAfterUpdate);
		BookDto response = bookController.updateBook(secondBook, 1);
		assertNotNull(response);
		assertEquals(150, response.getPrice());

	}

	@Test
	public void deleteBookTest() {
		bookController.deleteBook((long) 1);
		Mockito.verify(bookService, atMostOnce()).deleteBook((long) 1);
	}

	@Test
	public void findBooksTest() {

		List<BookDto> books = new ArrayList<BookDto>();

		books.add(getBook());
		books.add(getSecondBook());
		when(bookService.getAllBooks()).thenReturn(books);
		List<BookDto> response = bookController.findBooks();
		assertNotNull(response);
		assertEquals(2, response.size());
	}

	private BookDto getBook() {

		BookCategoryDto category = new BookCategoryDto();

		category.setId((long) 1);
		category.setName("Fiction");

		BookDto bookDto = new BookDto();
		bookDto.setId((long) 1);
		bookDto.setAuthor("Gabriel García Marquez");
		bookDto.setName("One Hundred Years of Solitude");
		bookDto.setISBN("978-3-16-148410-4");
		bookDto.setPrice(130);
		bookDto.setBookCategory(category);

		return bookDto;
	}

	private BookDto getSecondBook() {

		BookCategoryDto category = new BookCategoryDto();

		category.setId((long) 1);
		category.setName("Fiction");

		BookDto bookDto = new BookDto();
		bookDto.setAuthor("Paulo Coelho");
		bookDto.setName("The Alchemist");
		bookDto.setISBN("978-3-16-148410-6");
		bookDto.setPrice(140);
		bookDto.setBookCategory(category);

		return bookDto;
	}
}