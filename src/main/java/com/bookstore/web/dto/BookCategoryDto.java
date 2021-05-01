package com.bookstore.web.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookCategoryDto {
	private Long id;
	private String name;
	private String description;
	
	private LocalDateTime createdOn;
	private String createdBy;
}
