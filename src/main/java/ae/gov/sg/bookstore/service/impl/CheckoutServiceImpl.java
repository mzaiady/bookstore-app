/**
 * 
 */
package ae.gov.sg.bookstore.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ae.gov.sg.bookstore.domain.Book;
import ae.gov.sg.bookstore.domain.Promotion;
import ae.gov.sg.bookstore.dto.CheckoutResult;
import ae.gov.sg.bookstore.repository.BookRepository;
import ae.gov.sg.bookstore.repository.PromotionRepository;
import ae.gov.sg.bookstore.service.CheckoutService;

/**
 * The Class CheckoutServiceImpl.
 *
 * @author mohamed elzaiady
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

	/** The book repository. */
	private final BookRepository bookRepository; 


	/** The promotion repository. */
	private final PromotionRepository promotionRepository;

	/**
	 * Instantiates a new checkout service impl.
	 *
	 * @param bookRepository the book repository
	 * @param promotionRepository the promotion repository
	 */
	@Autowired
	public CheckoutServiceImpl(
			final BookRepository bookRepository, 
			final PromotionRepository promotionRepository) {
		this.bookRepository = bookRepository;
		this.promotionRepository = promotionRepository;
	}

	/**
	 * Checkout.
	 * if list contains duplication, each entry will be computed.
	 * Assume ids list not empty
	 *
	 * @param booksIds the books ids
	 * @param promoCode the promo code
	 * @return the checkout result
	 */
	@Override
	public CheckoutResult checkout(final List<Integer> booksIds, final String promoCode) {
		final List<Book> books = bookRepository.findAllById(booksIds);
		final Map<Integer, Book> bookMap = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));

		if (bookMap.isEmpty() || !bookMap.keySet().containsAll(booksIds)) {
			List<Integer> notFound = booksIds.stream().filter( id-> !bookMap.keySet().contains(id)).toList();
			throw new IllegalArgumentException("Invalid book identifiers " + notFound);
			
		}

		final Optional<Promotion> promotion = promotionRepository.findByPromotionTypeCode(promoCode);
		final Map<Book, Integer> requiredBooks = checkOutRecognizeDuplicate(booksIds, bookMap);
		

		 BigDecimal totalPrice = BigDecimal.ZERO;
		for (final Map.Entry<Book, Integer>  bookEntry : requiredBooks.entrySet()) {
			final Book book = bookEntry.getKey();
			final int numberOfBookCopies = bookEntry.getValue();
			totalPrice = totalPrice.add(computePrice(book, promotion).multiply(BigDecimal.valueOf(numberOfBookCopies)));
		}

		return new CheckoutResult(totalPrice.setScale(2,RoundingMode.HALF_DOWN));
	}


	/**
	 * Compute price.
	 *
	 * @param book the book
	 * @param promo the max promo
	 * @return the double
	 */
	BigDecimal computePrice(final Book book, final Optional<Promotion> promo) {
		if (promo.isPresent() && book.isApplicable(promo.get())) {
			final BigDecimal disount = book.getPrice().multiply( BigDecimal.valueOf(promo.get().getDiscountPercentage()/100.0d));
			return book.getPrice().subtract(disount);
		} else {
			return book.getPrice();
		}
	}
   
	/**
	 * Check out recognize duplicate.
	 * 
	 *  Assumption: duplicate book id count for another copy order
	 *
	 * @param ids the ids
	 * @param bookMap the book map
	 * @return the checkout result
	 */
	private Map<Book, Integer> checkOutRecognizeDuplicate(final Collection<Integer> ids, final Map<Integer, Book> bookMap) {
		//compute frequency of ids
		final HashMap<Integer, Long> IdsFrequency = ids.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
		final HashMap<Book, Integer> result = new HashMap<>(bookMap.size());
		IdsFrequency.forEach( (id, count) -> result.put(bookMap.get(id), count.intValue()));

		return result;
	}

}
