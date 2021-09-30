/**
 * 
 */
package ae.gov.sg.bookstore.service;

import java.util.List;


import ae.gov.sg.bookstore.dto.BookDTO;

/**
 * The Interface BookService. Contain Books CRUD operations.
 *
 * @author mohamed elzaiady
 */
public interface BookService {

	/**
	 * Gets the book.
	 *
	 * @param bookId the book id
	 * @return the book
	 */
	List<BookDTO> getAllBooks();


	/**
	 * Gets the book by ISBN.
	 *
	 * @param bookId the book id
	 * @return the book
	 */
	BookDTO getBook(Integer id);

	
	/**
	 * Update book.
	 *
	 * @param bookDto the book dto
	 */
	void updateBook(Integer Id, BookDTO bookDto);

	
	/**
	 * Update book.
	 *
	 * @param bookDto the book dto
	 */
	//void partialUpdate(Integer Id, BookDTO bookDto);

	
	/**
	 * Creates the book.
	 *
	 * @param bookDto the book dto
	 */
	 Integer createBook(BookDTO bookDto);



	/**
	 * Delete book by id.
	 *
	 * @param isbn the isbn
	 */
	void deleteBook(Integer id);

	
}
