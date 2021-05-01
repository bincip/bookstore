package com.bookstore.web.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.web.dto.BookDto;
import com.bookstore.web.entity.Book;
import com.bookstore.web.exception.BookNotFoundException;
import com.bookstore.web.mapper.BookMapper;
import com.bookstore.web.repository.BookRepository;
import com.bookstore.web.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookMapper bookMapper;

	@Override
	public List<BookDto> getAllBooks() {

		List<BookDto> books = bookRepository.findAllByOrderByNameAsc().stream().map(book -> {
			return bookMapper.bookToBookDto(book);
		}).collect(Collectors.toList());

		return books;

	}

	@Override
	public BookDto getBook(long id) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("id", String.valueOf(id)));
		return bookMapper.bookToBookDto(book);
	}

	@Override
	public BookDto createBook(BookDto bookDto) {

		Book book = bookMapper.bookDtoToBook(bookDto);
		if (book != null) {
			book = bookRepository.save(book);
		}
		return bookMapper.bookToBookDto(book);

	}

	@Override
	public BookDto updateBook(BookDto bookDto, long id) {

		bookDto.setId(id);
		Optional<Book> bookToUpdate = bookRepository.findById(id);
		if (bookToUpdate.isPresent()) {
			Book book = bookMapper.bookDtoToBook(bookDto);
			if (book != null) {
				book = bookRepository.save(book);
			}
			return bookMapper.bookToBookDto(book);

		} else {
			throw new BookNotFoundException("id", String.valueOf(id));
		}
	}

	@Override
	public void deleteBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.delete(book.get());
		} else {
			throw new BookNotFoundException("id", String.valueOf(id));
		}
	}

}
