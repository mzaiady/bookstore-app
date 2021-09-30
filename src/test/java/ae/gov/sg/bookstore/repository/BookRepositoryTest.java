package ae.gov.sg.bookstore.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ae.gov.sg.bookstore.domain.Book;

/**
 * The Class BookRepositoryTest.
 */
@SpringBootTest
class BookRepositoryTest {

	@Autowired
	BookRepository bookRepository;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test find by isbn.
	 */
	@Test
	void testFindByIsbn() {
		Optional<Book> bookOtp = bookRepository.findByIsbn("978-0593077184");
		assertTrue(bookOtp.isPresent());
	}

	/**
	 * Test find all by id iterable of integer.
	 */
	@Test
	void testFindAllByIdIterableOfInteger() {
		List<Book> findAllById = bookRepository.findAllById(Arrays.asList(1,2,3,3));
		assertFalse(findAllById.isEmpty());
		assertEquals(3, findAllById.size());

	}

}
