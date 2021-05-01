package com.bookstore.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.web.entity.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

	List<BookCategory> findAllByOrderByNameDesc();

}
