/**
 * 
 */
package ae.gov.sg.bookstore.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ae.gov.sg.bookstore.domain.ClassificationType;

/**
 * The Class ClassificationTypeRepositoryTest.
 * The repository is only an interface, the test goal to make sure initial data insertion, domain mapping and configuration working fine
 *
 * @author mohamed elzaiady
 */
@SpringBootTest
class ClassificationTypeRepositoryTest {

	@Autowired
	ClassificationTypeRepository classificationTypeRepository;
	
	/**
	 * Test method for {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}.
	 */
	@Test
	void testFindAll() {
		List<ClassificationType> classificationTypes = classificationTypeRepository.findAll();
		assertFalse(classificationTypes.isEmpty(), "Initial Data not insertered, No Classification Type found ");
	}

	/**
	 * Test method for {@link org.springframework.data.jpa.repository.JpaRepository#getById(java.lang.Object)}.
	 */
	@Test
	void testGetById() {
		Optional<ClassificationType> type3 = classificationTypeRepository.findById(3);
		assertTrue(type3.isPresent(), "No Classification found with id 3");
		assertEquals(3, type3.get().getId());
		assertEquals("Fiction", type3.get().getName());

	}

}
