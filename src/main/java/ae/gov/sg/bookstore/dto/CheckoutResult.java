/**
 * 
 */
package ae.gov.sg.bookstore.dto;

import java.math.BigDecimal;

/**
 * The Class CheckoutResult.
 *
 * @author mohamed elzaiady
 */
public class CheckoutResult {

	/** The total price. */
	private final BigDecimal totalPrice;
	
	/**
	 * Instantiates a new checkout result.
	 *
	 * @param totalPrice the total price
	 */
	public CheckoutResult(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
		
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	
}
