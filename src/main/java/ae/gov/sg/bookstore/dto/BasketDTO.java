/**
 * 
 */
package ae.gov.sg.bookstore.dto;

import java.util.List;

/**
 * The Class BasketDTO.
 *
 * @author mohamed elzaiady
 */
public class BasketDTO {

	/** The books. */
	private List<Integer> books;
	
	/** The promotion code. */
	private String promotionCode;

	/**
	 * Gets the books.
	 *
	 * @return the books
	 */
	public List<Integer> getBooks() {
		return books;
	}

	/**
	 * Sets the books.
	 *
	 * @param books the new books
	 */
	public void setBooks(final List<Integer> books) {
		this.books = books;
	}

	/**
	 * Gets the promotion code.
	 *
	 * @return the promotion code
	 */
	public String getPromotionCode() {
		return promotionCode;
	}

	/**
	 * Sets the promotion code.
	 *
	 * @param promotionCode the new promotion code
	 */
	public void setPromotionCode(final String promotionCode) {
		this.promotionCode = promotionCode;
	}
	
	
}
