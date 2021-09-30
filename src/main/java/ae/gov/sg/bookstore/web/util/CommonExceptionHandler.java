/**
 * 
 */
package ae.gov.sg.bookstore.web.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ae.gov.sg.bookstore.exceptions.ErrorMessage;
import ae.gov.sg.bookstore.exceptions.ResourceAlreadyExistException;
import ae.gov.sg.bookstore.exceptions.ResourceNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonExceptionHandler.
 *
 * @author mohamed elzaiady
 */
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	
	/**
	 * Resource not found exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleException(final ResourceNotFoundException ex) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);		
	}

	/**
	 * Handle exception Resource Already Exist Exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleException(final ResourceAlreadyExistException ex) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatus.CONFLICT);		
	}

	/**
	 * Handle IllegalArgumentException.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleException(final IllegalArgumentException ex) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);		
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException(final Exception ex) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);		
	}
	

	/**
	 * Handle method argument not valid.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessage errorMsg = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Invalid Input", errorList);
        return handleExceptionInternal(ex, errorMsg, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	/**
	 * Handle missing path variable.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
        ErrorMessage errorMsg = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), 
        		"Missing path variable Or Request method not supported");
		return handleExceptionInternal(ex, errorMsg, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	/**
	 * Handle http request method not supported.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMsg = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), 
        		"Missing path variable Or Request method not supported");
		return handleExceptionInternal(ex, errorMsg, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}
	
	//HttpRequestMethodNotSupportedException

	/**
	 * Handle exception internal.
	 *
	 * @param ex the ex
	 * @param body the body
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		Object newBody = Objects.isNull(body) ? new ErrorMessage(status.value(), status.name(), Arrays.asList(ex.getMessage())):body;
		return super.handleExceptionInternal(ex, newBody, headers, status, request);
	}
	
}
