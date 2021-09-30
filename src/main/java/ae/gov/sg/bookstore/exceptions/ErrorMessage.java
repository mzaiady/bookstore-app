/**
 * 
 */
package ae.gov.sg.bookstore.exceptions;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The Class ErrorMessage.
 *
 * @author mohamed elzaiady
 */
public class ErrorMessage {
	
	/** The statu code. */
	private final int errorCode;
	
	/** The error message. */
	private final String errorMessage;	

	/** The timestamp. */
	private final Date timestamp;

	/** The error details. */
	private final List<String> errorDetails;
	
	/**
	 * Instantiates a new error message.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param errorDetails the error details
	 */
	public ErrorMessage(final int errorCode, final String errorMessage,  final List<String> errorDetails) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.timestamp = new Date();
	}


	/**
	 * Instantiates a new error message.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public ErrorMessage(final int errorCode, final String errorMessage) {
		this(errorCode,errorMessage, Collections.emptyList());
	}

	/**
	 * Gets the statu code.
	 *
	 * @return the statu code
	 */
	public int getErrorCode() {
		return errorCode;
	}


	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}


	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	public List<String> getErrorDetails() {
		return errorDetails;
	}

}
