/**
 * 
 */
package ae.gov.sg.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ae.gov.sg.bookstore.domain.ClassificationType;

/**
 * The Interface ClassificationTypeRepository.
 *
 * @author mohamed elzaiady
 */
@Repository
public interface ClassificationTypeRepository extends JpaRepository<ClassificationType, Integer> {

	/**
	 * Find by classification name ignore case.
	 *
	 * @param classificationName the classification name
	 * @return the classification type
	 */
	Optional<ClassificationType> findByNameIgnoreCase(String classificationName);
	
}
