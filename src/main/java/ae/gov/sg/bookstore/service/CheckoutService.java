/**
 * 
 */
package ae.gov.sg.bookstore.service;

import java.util.List;

import ae.gov.sg.bookstore.dto.CheckoutResult;

/**
 * The Interface CheckoutService.
 *
 * @author mohamed elzaiady
 */
public interface CheckoutService {

	/**
	 * Checkout.
	 *
	 * @param books the books
	 * @param promoCode the promo code
	 * @return the checkout result
	 */
	public CheckoutResult checkout(List<Integer> books, String promoCode);
}
