/**
 * 
 */
package ae.gov.sg.bookstore.exceptions;

/**
 * The Class NotFoundException.
 * exception for when request data not found
 *
 * @author mohamed elzaiady
 */
public class ResourceNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param msg the msg
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
