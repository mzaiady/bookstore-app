/**
 * 
 */
package ae.gov.sg.bookstore.web;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ae.gov.sg.bookstore.dto.BookDTO;
import ae.gov.sg.bookstore.service.BookService;

/**
 * Book Controller API.
 *
 * @author Mohamed Elzaiady
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

	
	/** The book service. */
	@Autowired
	BookService bookService;
	
	/**
	 * Gets the all books.
	 *
	 * @return the all books
	 */
	@GetMapping
	public List<BookDTO> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	/**
	 * Creates the.
	 *
	 * @param book the book
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Integer> create(final @Valid @RequestBody BookDTO book) {
		Integer createBookId = bookService.createBook(book);
		return new ResponseEntity<>(createBookId, HttpStatus.CREATED);
	}
	
	/**
	 * Update.
	 *
	 * @param id the id
	 * @param book the book
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public void update(
			final @PathVariable("id") Integer id,
			final @Valid @RequestBody BookDTO book) {
		bookService.updateBook(id, book);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void delete(final @PathVariable("id") Integer id) {
		bookService.deleteBook(id);
	}

	/**
	 * Read.
	 *
	 * @param id the id
	 * @return the book DTO
	 */
	@GetMapping("/{id}")
	public BookDTO read(@PathVariable ("id") Integer id) {
		return bookService.getBook(id);
		
	}
	
}
