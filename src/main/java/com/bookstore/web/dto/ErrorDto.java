package com.bookstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto {
	
	public ErrorDto() {}
	
	private String code;
	private String message;
	private String errorlogId;
}
