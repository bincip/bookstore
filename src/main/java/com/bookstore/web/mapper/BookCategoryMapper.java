package com.bookstore.web.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bookstore.web.dto.BookCategoryDto;
import com.bookstore.web.entity.BookCategory;

@Service
public class BookCategoryMapper {

	public BookCategoryDto bookCategoryToBookCategoryDto(BookCategory bookCategory) {
		BookCategoryDto bookCategoryDto = new BookCategoryDto();
		BeanUtils.copyProperties(bookCategory, bookCategoryDto);
		return bookCategoryDto;
	}

	public BookCategory bookCategoryDtoToBookCategory(BookCategoryDto bookCategoryDto) {
		BookCategory bookCategory = new BookCategory();
		BeanUtils.copyProperties(bookCategoryDto, bookCategory);
		return bookCategory;
	}

}
