package ae.gov.sg.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Promotion type.
 * @author mohamed elzaiady
 * 
 * additional attributes , could start_date, end_date, or is_active
 * 
 */
@Entity
@Table(name="BS_PROMOTION_TYPE")
public class PromotionType {

	/** The id. */
	private Integer id;
	
	/** The code. */
	private String code;
	
	/** The description. */
	private String description;
	
	
	/** The discount percentage. */
	private float discountPercentage;

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
	 * Gets the code.
	 *
	 * @return the code
	 */
	@Column(name="CODE", nullable = false, length=15, unique = true)
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name="DESCRIPTION",nullable = false, length=255)
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the discount percentage.
	 *
	 * @return the discount percentage
	 */
	@Column(name="DISCOUNT_PERCENTAGE")
	public float getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * Sets the discount percentage.
	 *
	 * @param discountPercentage the new discount percentage
	 */
	public void setDiscountPercentage(final float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}
