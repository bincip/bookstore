package com.bookstore.web.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookDto {
	private Long id;
	private String name;
	private String description;
	private String author;
	private BookCategoryDto bookCategory;
	private float price;
	private String ISBN;

	private LocalDateTime createdOn;
	private String createdBy;
}
