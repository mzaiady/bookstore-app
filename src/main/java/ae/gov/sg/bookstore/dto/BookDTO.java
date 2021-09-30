package ae.gov.sg.bookstore.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ae.gov.sg.bookstore.domain.Book;

/**
 * The Class BookDTO.
 */
public class BookDTO {
	
	/** The name. */
	@NotNull(message="'name' is Mandatory.")
	@Size(max = 255, min = 2, message = "Book name minimum length is 255 and max is 2.")
	private String name;
	
	/** The description. */
	@NotNull(message="'description' is Mandatory.")
	@Size(max = 1000, min = 2, message = "'description' minimum length is 2 and max is 1000.")
	private String description;
	
	/** The author. */
	@NotNull(message="'author' name is Mandatory.")
	@Size(max = 500, min = 2, message = "'author' minimum length is 2 and max is 500.")
	private String author;
	
	/** The classification. */
	@NotNull(message="Type / 'classification' is Mandatory.")
	@Size(max = 50, min = 2, message = "'classification' minimum length is 2 and max is 50.")
	private String classification;
	
	/** The price. */
	@NotNull(message="'price' name is Mandatory.")
	@Min(value = 1l, message="'price' minimum value is 1.")
	private BigDecimal price;
	
	/** The isbn. */
	@NotNull(message = "'isbn' is Mandatory")
	@Size(min = 10, max = 14,message = "'isbn' must be of length of 10 (ISBN-10 format) or 14 (ISBN-13 format).")
	private String isbn;
	
	/** The id. */
	private Integer id;
	
	/**
	 * Instantiates a new book DTO.
	 */
	public BookDTO() {
	}


	/**
	 * Instantiates a new book DTO.
	 *
	 * @param book the book
	 */
	public BookDTO(final Book book) {
		this.isbn = book.getIsbn();
		this.author = book.getAuthor();
		this.description = book.getDescription();
		this.classification = book.getClassification();
		this.name = book.getName();
		this.price = book.getPrice();
		this.id = book.getId();
	}

	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
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
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author.
	 *
	 * @param author the author to set
	 */
	public void setAuthor(final String author) {
		this.author = author;
	}
	
	/**
	 * Gets the classification.
	 *
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}
	
	/**
	 * Sets the classification.
	 *
	 * @param classification the classification to set
	 */
	public void setClassification(final String classification) {
		this.classification = classification;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the price to set
	 */
	public void setPrice(final BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * Gets the isbn.
	 *
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * Sets the isbn.
	 *
	 * @param isbn the isbn to set
	 */
	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}	
}
