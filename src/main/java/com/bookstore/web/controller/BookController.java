package com.bookstore.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.web.dto.BookDto;
import com.bookstore.web.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Manage Books" })
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Gets book by bookid", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved the Book"),
			@ApiResponse(code = 404, message = "Book not found for the id."),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@GetMapping("/{id}")
	public BookDto findById(@PathVariable long id) {
		return bookService.getBook(id);
	}

	@ApiOperation(value = "Save book details", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully saved the book details"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@PostMapping("/")
	public BookDto createBook(@RequestBody BookDto book) {
		return bookService.createBook(book);
	}

	@ApiOperation(value = "Updates Book details", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated book details"),
			@ApiResponse(code = 404, message = "Book not found for the id."),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookDto updateBook(@RequestBody BookDto book, @PathVariable long id) {
		return bookService.updateBook(book, id);

	}

	@ApiOperation(value = "Delete book by ID", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully Deleted the book"),
		@ApiResponse(code = 404, message = "Book not found for the id."),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	@ApiOperation(value = "Gets all books by Alphabetical order of Name", notes = "", produces = "application/json", response = Boolean.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved the books"),
			@ApiResponse(code = 400, message = "Bad Request."), })
	@GetMapping("/all")
	public List<BookDto> findBooks() {
		return bookService.getAllBooks();
	}
}