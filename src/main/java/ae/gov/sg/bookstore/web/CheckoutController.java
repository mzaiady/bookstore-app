/**
 * 
 */
package ae.gov.sg.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ae.gov.sg.bookstore.dto.BasketDTO;
import ae.gov.sg.bookstore.dto.CheckoutResult;
import ae.gov.sg.bookstore.service.CheckoutService;

/**
 * The Class CheckoutController.
 *
 * @author mohamed elzaiady
 */
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	/** The checkout service. */
	@Autowired
	CheckoutService checkoutService;
	
	
	/**
	 * Checkout.
	 *
	 * @param books the books
	 * @param promotionCode the promotion code
	 * @return the checkout result
	 */
	@GetMapping
	public CheckoutResult checkout(
			@RequestParam("books") final List<Integer> books, 
			@RequestParam(name = "promotionCode",required = false) final String promotionCode) {
		return checkoutService.checkout(books, promotionCode);
	}

	/**
	 * Checkout.
	 *
	 * @param basket the basket
	 * @return the checkout result
	 */
	@PostMapping
	public CheckoutResult checkout(@RequestBody final BasketDTO basket) {
		return checkoutService.checkout(basket.getBooks(), basket.getPromotionCode());
	}

}
