package ae.gov.sg.bookstore.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ae.gov.sg.bookstore.domain.Promotion;

/**
 * The Class PromotionRepositoryTest.
 */
@SpringBootTest
class PromotionRepositoryTest {

	@Autowired
	PromotionRepository promotionRepository;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test find by promotion type code.
	 */
	@Test
	void testFindByPromotionTypeCode() {
		List<Promotion> promotions = promotionRepository.findByPromotionTypeCodeIn(Arrays.asList("PRO-210-000","PRO-420-000"));
		assertFalse(promotions.isEmpty());
		assertEquals(2, promotions.size());
	}

	/**
	 * Test find by promotion type code.
	 */
	@Test
	void testFindByPromotionTypeCodeNoDataIfNoPromotionClassifictionExist() {
		List<Promotion> promotions = promotionRepository.findByPromotionTypeCodeIn(Arrays.asList("PRO-630-000","PRO-525-000"));
		assertTrue(promotions.isEmpty());
	}
	
}
