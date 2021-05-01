package com.bookstore.web.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private String description;

	private int discountPercentage;

	private LocalDateTime expiryDate;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "COUPON_CATEGORY_MAPPING", joinColumns = @JoinColumn(name = "COUPON_ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_CATEGORY_ID"))
	private List<BookCategory> bookCategories;

}
