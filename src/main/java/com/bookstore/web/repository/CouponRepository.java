package com.bookstore.web.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.web.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Optional<Coupon> findByCodeAndExpiryDateAfter(String couponCode, LocalDateTime expiryDate);

}
