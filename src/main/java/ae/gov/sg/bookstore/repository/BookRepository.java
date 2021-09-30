/**
 * 
 */
package ae.gov.sg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ae.gov.sg.bookstore.domain.Book;

/**
 * The Interface BookRepository.
 *
 * @author mohamed elzaiady
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

	/**
	 * Find by isbn.
	 *
	 * @param isbn the isbn
	 * @return the optional
	 */
	Optional<Book> findByIsbn(String isbn);
	
	
	/**
	 * Find all by id.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	@EntityGraph(type=EntityGraphType.FETCH, attributePaths = {"classificationType"})
	List<Book> findAllById(Iterable<Integer> ids);

	
	
}
