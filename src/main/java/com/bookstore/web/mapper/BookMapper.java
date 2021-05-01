package com.bookstore.web.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.web.dto.BookDto;
import com.bookstore.web.entity.Book;

@Service
public class BookMapper {
	
	@Autowired
	BookCategoryMapper bookCategoryMapper;

	public BookDto bookToBookDto(Book book) {
		BookDto bookDto = new BookDto();
		BeanUtils.copyProperties(book, bookDto);
		
		if(book.getBookCategory() != null) {
			bookDto.setBookCategory(bookCategoryMapper.bookCategoryToBookCategoryDto(book.getBookCategory()));
		}
		
		return bookDto;
	}

	public Book bookDtoToBook(BookDto bookDto) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		
		if(bookDto.getBookCategory() != null) {
			book.setBookCategory(bookCategoryMapper.bookCategoryDtoToBookCategory(bookDto.getBookCategory()));
		}
		
		return book;
	}

}
