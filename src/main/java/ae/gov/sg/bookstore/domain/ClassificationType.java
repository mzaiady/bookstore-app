/**
 * 
 */
package ae.gov.sg.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Classification Type [e.g. General Works, Philosophy, Fiction, Science, Medicine, Agriculture, Technology ]
 *
 * @author mohamed elzaiady
 */
@Entity
@Table(name="BS_CLASSIFICATION_TYPE")
public class ClassificationType {
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;

	
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
	 * Gets the classification type name.
	 *
	 * @return the name
	 */
	@Column(name="NAME", nullable = false, unique = true, length = 50)
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

}
