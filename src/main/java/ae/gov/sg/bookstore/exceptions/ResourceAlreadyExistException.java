/**
 * 
 */
package ae.gov.sg.bookstore.exceptions;

/**
 * The Class ResourceAlreadyExistException.
 *
 * @author mohamed elzaiady
 */
public class ResourceAlreadyExistException extends RuntimeException {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param msg the msg
	 */
	public ResourceAlreadyExistException(final String msg) {
		super(msg);
	}

}
