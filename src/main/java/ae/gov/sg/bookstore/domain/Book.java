package ae.gov.sg.bookstore.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Book Entity in Book Store Module.
 *
 * @author mohamed elzaiady
 */
@Entity
@Table(name="BS_BOOK")
public class Book {
	
	/** The id. */
	private Integer id; 

	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The author. */
	private String author;
	
	/** The price. */
	private BigDecimal price;
	
	/** The ISBN. */
	private String isbn;
	
	/** The version. */
	private int version;
	
	/** The classification. */
	private ClassificationType classificationType;
	
	
	/*TODO: add audit Information CREATE_BY, UPDATED_BY, CREATED_DATE, UPDATED_DATE,  ....*/
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="book_sequence_genertor")
	@SequenceGenerator(sequenceName = "BS_SEQ_BOOK_ID", name = "book_sequence_genertor")
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name="NAME", nullable = false, length = 255)
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
	@Column(name="DESCRIPTION", nullable = false, length = 1000)
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
	 * Gets the author. for multiple authors, it will be comma separator text
	 *
	 * @return the author
	 */
	@Column(name="AUTHOR", nullable = false, length = 500)
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
	 * Gets the price.
	 *
	 * @return the price
	 */
	@Column(name="PRICE", precision = 21, scale = 2, nullable = false)
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
	@Column(name="ISBN", length = 14, nullable = false, unique = true)
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
	 * Gets the version.
	 *
	 * @return the version
	 */
	@Version
	@Column(name="OTPLOCK_VERSION")
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(final int version) {
		this.version = version;
	}

	/**
	 * Gets the classification.
	 *
	 * @return the classification
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name="CLASSIFICATION_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name="BS_FK_BOOK_CLASSIFICATION_TYPE"))
	public ClassificationType getClassificationType() {
		return classificationType;
	}

	/**
	 * Sets the classification.
	 *
	 * @param classification the new classification
	 */
	public void setClassificationType(final ClassificationType classification) {
		this.classificationType = classification;
	}

	/**
	 * Gets the classification.
	 *
	 * @return the classification
	 */
	@Transient
	public String getClassification() {
		return getClassificationType().getName();
	}

	/**
	 * Checks if is applicable.
	 *
	 * @param promotion the promotion
	 * @return true, if is applicable
	 */
	@Transient
	public boolean isApplicable(Promotion promotion) {
		return promotion.isApplicable(this.getClassificationType());
	}
}
