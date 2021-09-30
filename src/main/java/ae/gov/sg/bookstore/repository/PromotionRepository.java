/**
 * 
 */
package ae.gov.sg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ae.gov.sg.bookstore.domain.Promotion;

/**
 * The Interface ClassificationTypePromotionRepository.
 *
 * @author mohamed elzaiady
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{


	/**
	 * Find by promotion type code.
	 * Use @EntityGraph to avoid n+1 problem, and feach all required data in one query.
	 *
	 * @param codes the codes
	 * @return the list
	 */
	@EntityGraph(type = EntityGraphType.FETCH, attributePaths = {"classificationType","promotionType"})
	List<Promotion> findByPromotionTypeCodeIn(Iterable<String> codes);

	/**
	 * Find by promotion type code.
	 *
	 * @param code the code
	 * @return the list
	 */
	@EntityGraph(type = EntityGraphType.FETCH, attributePaths = {"classificationType","promotionType"})
	Optional<Promotion> findByPromotionTypeCode(String code);

}
