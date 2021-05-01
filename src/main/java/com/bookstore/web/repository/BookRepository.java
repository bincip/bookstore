package com.bookstore.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.web.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findAllByOrderByNameAsc();

	Optional<Book> findByISBN(String isbn);

}
