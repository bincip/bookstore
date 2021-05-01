package com.bookstore.web.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstore.web.entity.Book;
import com.bookstore.web.entity.BookCategory;
import com.bookstore.web.entity.Coupon;
import com.bookstore.web.repository.BookRepository;
import com.bookstore.web.repository.CouponRepository;
import com.bookstore.web.util.DateHelper;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

	@InjectMocks
	CartServiceImpl cartService;

	@Mock
	CouponRepository couponRepository;

	@Mock
	BookRepository bookRepository;

	@Mock
	DateHelper dateHelper;

	@BeforeEach
	public void setup() {
		when(bookRepository.findByISBN("978-3-16-148410-4")).thenReturn(Optional.of(getFictionBookOne()));
		when(bookRepository.findByISBN("978-3-16-148410-6")).thenReturn(Optional.of(getFictionBookTwo()));
		when(bookRepository.findByISBN("978-3-16-148410-8")).thenReturn(Optional.of(getChildrenBookOne()));
		when(bookRepository.findByISBN("978-3-16-148410-5")).thenReturn(Optional.of(getSelfHelpBookOne()));
	}

	@Test
	void getPayableAmount_when_Coupon_Empty() {
		float amount = cartService.getPayableAmount(getBooks(), "");
		assertEquals(460, amount);
	}

	@Test
	void getPayableAmount_when_Coupon_Null() {
		float amount = cartService.getPayableAmount(getBooks(), null);
		assertEquals(460, amount);
	}

	@Test
	void getPayableAmount_when_Coupon_forAll() {

		LocalDateTime now = LocalDateTime.now();
		when(dateHelper.getCurrentTime()).thenReturn(now);
		when(couponRepository.findByCodeAndExpiryDateAfter("ALL15", now))
				.thenReturn(Optional.of(getCouponAllCategory()));
		float amount = cartService.getPayableAmount(getBooks(), "ALL15");
		assertEquals(391, amount);
	}

	@Test
	void getPayableAmount_when_Coupon_Fiction_Children() {
		LocalDateTime now = LocalDateTime.now();
		when(dateHelper.getCurrentTime()).thenReturn(now);
		when(couponRepository.findByCodeAndExpiryDateAfter("MAY20", now))
				.thenReturn(Optional.of(getFictionandChildrensBookCoupon()));
		float amount = cartService.getPayableAmount(getBooks(), "MAY20");
		assertEquals(388, amount);
	}

	private List<Book> getBooks() {

		List<Book> book = new ArrayList<Book>();

		book.add(getChildrenBookOne());
		book.add(getFictionBookOne());
		book.add(getFictionBookTwo());
		book.add(getSelfHelpBookOne());

		return book;
	}

	private Book getFictionBookOne() {

		Book book = new Book();
		book.setId((long) 1);
		book.setAuthor("Gabriel García Marquez");
		book.setName("One Hundred Years of Solitude");
		book.setISBN("978-3-16-148410-4");
		book.setPrice(130);
		book.setBookCategory(getFictionCategory());

		return book;
	}

	private Book getFictionBookTwo() {

		Book book = new Book();
		book.setAuthor("Paulo Coelho");
		book.setName("The Alchemist");
		book.setISBN("978-3-16-148410-6");
		book.setPrice(140);
		book.setBookCategory(getFictionCategory());

		return book;
	}

	private Book getChildrenBookOne() {

		Book book = new Book();
		book.setAuthor("Jan Brett");
		book.setName("The Mitten");
		book.setISBN("978-3-16-148410-8");
		book.setPrice(90);
		book.setBookCategory(getChildrensBookCategory());

		return book;
	}

	private Book getSelfHelpBookOne() {

		Book book = new Book();
		book.setAuthor("Napoleon Hill");
		book.setName("Think and Grow Rich");
		book.setISBN("978-3-16-148410-5");
		book.setPrice(100);
		book.setBookCategory(getSelfHelpBookCategory());

		return book;
	}

	private BookCategory getFictionCategory() {
		BookCategory category = new BookCategory();
		category.setId((long) 1);
		category.setName("Fiction");
		return category;
	}

	private BookCategory getChildrensBookCategory() {
		BookCategory category = new BookCategory();
		category.setId((long) 7);
		category.setName("Childrens");
		return category;
	}

	private BookCategory getSelfHelpBookCategory() {
		BookCategory category = new BookCategory();
		category.setId((long) 4);
		category.setName("Self-help/Personal");
		return category;
	}

	private Coupon getFictionandChildrensBookCoupon() {

		List<BookCategory> bookCategories = new ArrayList<BookCategory>();

		bookCategories.add(getFictionCategory());
		bookCategories.add(getChildrensBookCategory());

		Coupon coupon = new Coupon();

		coupon.setCode("MAY20");
		coupon.setExpiryDate(LocalDateTime.now().plusDays(30));
		coupon.setDiscountPercentage(20);
		coupon.setBookCategories(bookCategories);
		return coupon;
	}

	private Coupon getCouponAllCategory() {

		List<BookCategory> bookCategories = new ArrayList<BookCategory>();

		bookCategories.add(getFictionCategory());
		bookCategories.add(getChildrensBookCategory());
		bookCategories.add(getSelfHelpBookCategory());

		Coupon coupon = new Coupon();

		coupon.setCode("ALL15");
		coupon.setExpiryDate(LocalDateTime.now().plusDays(10));
		coupon.setDiscountPercentage(15);
		coupon.setBookCategories(bookCategories);
		return coupon;
	}

}
