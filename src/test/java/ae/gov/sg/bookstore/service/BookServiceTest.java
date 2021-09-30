/**
 * 
 */
package ae.gov.sg.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ae.gov.sg.bookstore.domain.Book;
import ae.gov.sg.bookstore.dto.BookDTO;
import ae.gov.sg.bookstore.repository.BookRepository;

/**
 * The Class BookServiceTest.
 *
 * @author mohamed elzaiady
 */
@SpringBootTest
@Rollback
@Transactional
class BookServiceTest {


	/** The book service. */
	@Autowired
	BookService bookService;

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
	 * Test method for {@link ae.gov.sg.bookstore.service.BookService#getAllBooks()}.
	 */
	@Test
	void testGetAllBooks() {
		List<BookDTO> allBooks = bookService.getAllBooks();
		assertFalse(allBooks.isEmpty());
	}


	/**
	 * Test method for {@link ae.gov.sg.bookstore.service.BookService#getBook(java.lang.Integer)}.
	 */
	@Test
	void testGetBookInteger() {
		BookDTO book = bookService.getBook(1);
		assertNotNull(book);

	}

	/**
	 * Test method for {@link ae.gov.sg.bookstore.service.BookService#updateBook(java.lang.Integer, ae.gov.sg.bookstore.dto.BookDTO)}.
	 */
	@Test
	void testUpdateBook() {
		BookDTO book = bookService.getBook(1);
		assertNotNull(book);
		assertEquals("Stephen Hawking", book.getAuthor());
		book.setAuthor("author");
		bookService.updateBook(1, book);
		book = bookService.getBook(1);
		assertEquals("author", book.getAuthor());
	}

	/**
	 * Test method for {@link ae.gov.sg.bookstore.service.BookService#createBook(ae.gov.sg.bookstore.dto.BookDTO)}.
	 */
	@Test
	void testCreateBook() {
		BookDTO newBook = new BookDTO();
		newBook.setAuthor("author");
		newBook.setName("name");
		newBook.setDescription("description");
		newBook.setIsbn("isbn");
		newBook.setClassification("General Works");
		newBook.setPrice(BigDecimal.valueOf(23.0));
		
		Integer createBookId = bookService.createBook(newBook);
	
		Optional<Book> findById = bookRepository.findById(createBookId);
		assertTrue(findById.isPresent());
	}
	

	
	/**
	 * Test method for {@link ae.gov.sg.bookstore.service.BookService#deleteBook(java.lang.Integer)}.
	 */
	@Test
	void testDeleteBookInteger() {
		bookService.deleteBook(1);
		Optional<Book> findById = bookRepository.findById(1);
		assertTrue(findById.isEmpty());

	}

}
