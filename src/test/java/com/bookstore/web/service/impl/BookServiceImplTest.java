package com.bookstore.web.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.bookstore.web.dto.BookCategoryDto;
import com.bookstore.web.dto.BookDto;
import com.bookstore.web.entity.Book;
import com.bookstore.web.exception.BookNotFoundException;
import com.bookstore.web.mapper.BookMapper;
import com.bookstore.web.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl bookService;

	@Mock
	BookRepository bookRepository;
	
	@Mock
	BookMapper bookMapper;


	@Test
	public void createBookTest() {

		BookDto secondBook = getSecondBook();

		BookDto secondBookAfterSave = secondBook;
		secondBookAfterSave.setId((long) 1);
		when(bookMapper.bookDtoToBook(secondBook)).thenReturn(bookDtoToBook(secondBook));
		when(bookMapper.bookToBookDto(bookDtoToBook(secondBookAfterSave))).thenReturn(bookToBookDto(bookDtoToBook(secondBookAfterSave)));
		when(bookRepository.save(bookDtoToBook(secondBook))).thenReturn(bookDtoToBook(secondBookAfterSave));
		BookDto response = bookService.createBook(secondBook);
		assertNotNull(response);
		assertEquals("978-3-16-148410-6", response.getISBN());

	}
	
	@Test
	public void findByIdTest() {

		Optional<Book> book = Optional.of(bookDtoToBook(getBook()));
		
		when(bookRepository.findById((long) 1)).thenReturn(book);
		when(bookMapper.bookToBookDto(book.get())).thenReturn(bookToBookDto(book.get()));
		BookDto response = bookService.getBook((long) 1);
		assertNotNull(response);
		assertEquals("978-3-16-148410-4", response.getISBN());

	}

	@Test
	public void updateBookTest() {

		BookDto secondBook = getSecondBook();
		secondBook.setId((long) 1);
		
		BookDto secondBookAfterUpdate = secondBook;
		secondBookAfterUpdate.setPrice(150);

		when(bookRepository.findById((long) 1)).thenReturn(Optional.of(bookDtoToBook(secondBook)));
		when(bookMapper.bookDtoToBook(secondBook)).thenReturn(bookDtoToBook(secondBook));
		when(bookMapper.bookToBookDto(bookDtoToBook(secondBookAfterUpdate))).thenReturn(bookToBookDto(bookDtoToBook(secondBookAfterUpdate)));
		when(bookRepository.save(bookDtoToBook(secondBook))).thenReturn(bookDtoToBook(secondBookAfterUpdate));
		BookDto response = bookService.updateBook(secondBook,(long) 1);
		assertNotNull(response);
		assertEquals(150, response.getPrice());
	}

	@Test
	public void deleteBookTest() {
		
		assertThrows(BookNotFoundException.class, () -> {
			bookService.deleteBook((long) 1);
		});
	}

	@Test
	public void findBooksTest() {

		List<Book> books = new ArrayList<Book>();

		books.add(bookDtoToBook(getBook()));
		books.add(bookDtoToBook(getSecondBook()));
		when(bookRepository.findAllByOrderByNameAsc()).thenReturn(books);
		List<BookDto> response = bookService.getAllBooks();
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
	
	private BookDto bookToBookDto(Book book) {
		BookDto bookDto = new BookDto();
		BeanUtils.copyProperties(book, bookDto);
		return bookDto;
	}

	private Book bookDtoToBook(BookDto bookDto) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		return book;
	}

}