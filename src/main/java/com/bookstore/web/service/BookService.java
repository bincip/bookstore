package com.bookstore.web.service;

import java.util.List;

import com.bookstore.web.dto.BookDto;

public interface BookService {

	BookDto getBook(long id);

	BookDto createBook(BookDto book);

	BookDto updateBook(BookDto book, long id);

	void deleteBook(Long id);

	List<BookDto> getAllBooks();

}
