/**
 * 
 */
package ae.gov.sg.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class Promotion, the intersection between promotion type and classification type.
 * 
 * Can have attributes like expriy data
 *
 * @author mohamed elzaiady
 */
@Entity
@Table(name="BS_PROMOTION")
public class Promotion {

	/** The id. */
	private Integer id;
	
	/** The classification type. */
	private ClassificationType classificationType;
	
	/** The promotion type. */
	private PromotionType promotionType;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Gets the classification type.
	 *
	 * @return the classificationType
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name="CLASSIFICATION_TYPE_ID",foreignKey =@ForeignKey(name="BS_FK_PROMOTION_CLASSIFICATION_TYPE"))
	public ClassificationType getClassificationType() {
		return classificationType;
	}

	/**
	 * Sets the classification type.
	 *
	 * @param classificationType the classificationType to set
	 */
	public void setClassificationType(final ClassificationType classificationType) {
		this.classificationType = classificationType;
	}

	/**
	 * Gets the promotion.
	 *
	 * @return the promotion
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "PROMOTION_TYPE_ID",foreignKey = @ForeignKey(name="BS_FK_PROMOTION_PROMOTION_TYPE"))
	public PromotionType getPromotionType() {
		return promotionType;
	}

	/**
	 * Sets the promotion.
	 *
	 * @param promotion the promotion to set
	 */
	public void setPromotionType(final PromotionType promotion) {
		this.promotionType = promotion;
	}
	
	/**
	 * Checks if is applicable.
	 *
	 * @param classificationType the classification type
	 * @return true, if  classificationType applicable. additional condition like expiry date can add here also
	 */
	@Transient
	public boolean isApplicable(ClassificationType classificationType) { 
		return this.classificationType.getId().equals(classificationType.getId());
		
	}
	
	/**
	 * Gets the discount percentage.
	 *
	 * @return the discount percentage
	 */
	@Transient
	public Float getDiscountPercentage() {
		return this.promotionType.getDiscountPercentage();
	}
	
}
