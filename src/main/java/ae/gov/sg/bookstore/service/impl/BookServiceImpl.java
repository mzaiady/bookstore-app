/**
 * 
 */
package ae.gov.sg.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import ae.gov.sg.bookstore.domain.Book;
import ae.gov.sg.bookstore.domain.ClassificationType;
import ae.gov.sg.bookstore.dto.BookDTO;
import ae.gov.sg.bookstore.exceptions.ResourceAlreadyExistException;
import ae.gov.sg.bookstore.exceptions.ResourceNotFoundException;
import ae.gov.sg.bookstore.repository.BookRepository;
import ae.gov.sg.bookstore.repository.ClassificationTypeRepository;
import ae.gov.sg.bookstore.service.BookService;

/**
 * The Class BookServiceImpl.
 *
 * @author mohamed elzaiady
 */
@Service
public class BookServiceImpl implements BookService {


	/** The book repository. */
	private final BookRepository bookRepository;

	/** The classification type repository. */
	private final ClassificationTypeRepository classificationTypeRepository;

	/**
	 * Instantiates a new book service impl.
	 *
	 * @param bookRepository the book repository
	 * @param classificationTypeRepository the classification type repository
	 */
	@Autowired
	public BookServiceImpl(final BookRepository bookRepository,
			final ClassificationTypeRepository classificationTypeRepository) {
		this.bookRepository = bookRepository;
		this.classificationTypeRepository = classificationTypeRepository;
	}

	/**
	 * Gets the book.
	 *
	 * @return the book
	 */
	@Override
	public List<BookDTO> getAllBooks() {
		final Iterable<Book> allbooks = bookRepository.findAll();
		return Streamable.of(allbooks).map(BookDTO::new).toList();
	}



	/**
	 * Gets the book.
	 *
	 * @param id the id
	 * @return the book
	 */
	@Override
	public BookDTO getBook(Integer id) {
		final Optional<Book> findById = bookRepository.findById(id);
		return new BookDTO(findById.orElseThrow(notFoundException(id)));
	}


	/**
	 * Update book.
	 *
	 * @param Id the id
	 * @param bookDto the book dto
	 */
	@Override
	public void updateBook(final Integer Id, final BookDTO bookDto) {
		final Optional<Book> bookOpt = bookRepository.findById(Id);
		final Book book = bookOpt.orElseThrow(notFoundException(Id));
		update(bookDto, book);
	}

	/**
	 * Not found exception supplier function.
	 *
	 * @param Id the id
	 * @return the supplier<? extends resource not found exception>
	 */
	private static Supplier<? extends ResourceNotFoundException> notFoundException(final Integer Id) {
		return () -> new ResourceNotFoundException("No book found with id " + Id);
	}



	/**
	 * Creates the book.
	 *
	 * @param bookDto the book dto
	 * @return the integer
	 */
	@Override
	public Integer createBook(final BookDTO bookDto) {
		return update(bookDto, new Book()).getId();
	}

	/**
	 * Delete.
	 *
	 * @param book the book to delete
	 */
	private void delete(final Book book) {
		this.bookRepository.delete(book);
	}

	/**
	 * Delete book.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteBook(Integer id) {
		final Optional<Book> bookOpt = bookRepository.findById(id);
		delete(bookOpt.orElseThrow(notFoundException(id)));
	}


	/**
	 * Gets the classification type.
	 *
	 * @param classificationName the classification name
	 * @return the classification type
	 */
	private Optional<ClassificationType> getClassificationType(final String classificationName) {
		final Optional<ClassificationType> classificationTypeOpt = classificationTypeRepository.findByNameIgnoreCase(classificationName);
		return classificationTypeOpt;
	}

	/**
	 * Update.
	 *
	 * @param bookDto the book dto
	 * @param book the book
	 * @return the book
	 */
	private Book update(final BookDTO bookDto, final Book book) {
		final Optional<Book> bookByISBNOpt = bookRepository.findByIsbn(bookDto.getIsbn());

		if (bookByISBNOpt.isPresent() && !bookByISBNOpt.get().getId().equals(book.getId())) {
			throw new ResourceAlreadyExistException("Given ISBN already exist.");
		} 
		
		
		final Optional<ClassificationType> classificationTypeOpt = getClassificationType(bookDto.getClassification());
		final ClassificationType classificationType  = classificationTypeOpt.orElseThrow(
				() -> new IllegalArgumentException("Invalid classification '" + bookDto.getClassification() + "'"));
		
		
		book.setAuthor(bookDto.getAuthor());
		book.setClassificationType(classificationType);
		book.setIsbn(bookDto.getIsbn());
		book.setName(bookDto.getName());
		book.setDescription(bookDto.getDescription());
		book.setPrice(bookDto.getPrice());
		return this.bookRepository.save(book);
	}



}
