/**
 * 
 */
package ae.gov.sg.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ae.gov.sg.bookstore.domain.Book;
import ae.gov.sg.bookstore.domain.Promotion;
import ae.gov.sg.bookstore.dto.CheckoutResult;
import ae.gov.sg.bookstore.repository.BookRepository;
import ae.gov.sg.bookstore.repository.PromotionRepository;
import ae.gov.sg.bookstore.service.impl.CheckoutServiceImpl;


/**
 * The Class CheckoutServiceTest.
 *
 * @author mohamed elzaiady
 */
@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {
	
	/** The book repository. */
	@Mock
	BookRepository bookRepository;
	
	/** The promotion repository. */
	@Mock
	PromotionRepository promotionRepository;

	
	/** The checkout service. */
	CheckoutService checkoutService;
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		checkoutService = new CheckoutServiceImpl(bookRepository, promotionRepository);
	}

	/**
	 * Test method for {@link ae.gov.sg.bookstore.service.CheckoutService#checkout(java.util.List, java.lang.String)}.
	 */
	@Test
	void testCheckout() {
		Integer bookId= 10;
		List<Integer>  booksIds= Arrays.asList(bookId);

		double price = 100.0d;
		boolean promotionAplicable = true;
		float discountPrec = 25.0f;

		
		Promotion promotion =mock(Promotion.class);
		when(promotion.getDiscountPercentage()).thenReturn(discountPrec);
		
		Book book = mockBook(bookId, price, promotionAplicable, promotion);


		
		when(bookRepository.findAllById(booksIds)).thenReturn(Arrays.asList(book));
		when(promotionRepository.findByPromotionTypeCode("111-111-111")).thenReturn(Optional.of(promotion));
		
		CheckoutResult checkout = checkoutService.checkout(booksIds, "111-111-111");
		assertTrue(BigDecimal.valueOf(75.00d).compareTo(checkout.getTotalPrice())==0);
	}

	
	/**
	 * Test checkout multip books.
	 */
	@Test
	void testCheckoutMultipBooks() {
		List<Integer>  booksIds= Arrays.asList(10,20,30);

		double price = 100.0d;
		boolean promotionAplicable = true;
		float discountPrec = 25.0f;

		
		Promotion promotion =mock(Promotion.class);
		when(promotion.getDiscountPercentage()).thenReturn(discountPrec);
		
		Book book1 = mockBook(10, price, promotionAplicable, promotion);
		Book book2 = mockBook(20, price, promotionAplicable, promotion);
		Book book3 = mockBook(30, price, false, promotion);


		
		when(bookRepository.findAllById(booksIds)).thenReturn(Arrays.asList(book1,book2, book3));
		when(promotionRepository.findByPromotionTypeCode("111-111-111")).thenReturn(Optional.of(promotion));
		
		CheckoutResult checkout = checkoutService.checkout(Arrays.asList(10,20,30), "111-111-111");
		assertTrue(BigDecimal.valueOf(250.0).compareTo(checkout.getTotalPrice())==0);
	}

	
	/**
	 * Test checkout promotion NA.
	 */
	@Test
	void testCheckoutPromotionNA() {
		Integer bookId= 10;
		List<Integer>  booksIds= Arrays.asList(bookId);

		double price = 100.0d;
		boolean promotionAplicable = false;
	
		Promotion promotion =mock(Promotion.class);
		
		Book book = mockBook(bookId, price, promotionAplicable, promotion);

		
		when(bookRepository.findAllById(booksIds)).thenReturn(Arrays.asList(book));
		when(promotionRepository.findByPromotionTypeCode("111-111-111")).thenReturn(Optional.of(promotion));
		
		CheckoutResult checkout = checkoutService.checkout(booksIds, "111-111-111");
		assertTrue(BigDecimal.valueOf(100.0d).compareTo(checkout.getTotalPrice())==0);
	}

	
	/**
	 * Mock book.
	 *
	 * @param bookId the book id
	 * @param price the price
	 * @param promotionAplicable the promotion aplicable
	 * @param promotion the promotion
	 * @return the book
	 */
	private Book mockBook(Integer bookId, double price, boolean promotionAplicable, Promotion promotion) {
		Book book = mock(Book.class);
		when(book.getPrice()).thenReturn(BigDecimal.valueOf(price));
		when(book.isApplicable(promotion)).thenReturn(promotionAplicable);
		when(book.getId()).thenReturn(bookId);
		return book;
	}

}
