package hcl.mybankapp.mybankapp.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hcl.mybankapp.mybankapp.dto.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ResponseDTO error = new ResponseDTO("Request Validation Failed", status, details);
		logger.error(ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<Object> handleAllExceptions(ApplicationException ex, WebRequest request) {
		ResponseDTO error = new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
		logger.error(ex);
		return new ResponseEntity<>(error, error.getHttpStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleAllExceptions(ConstraintViolationException ex, WebRequest request) {
		ResponseDTO error = new ResponseDTO("AccountNo should be unique", HttpStatus.BAD_REQUEST, null);
		logger.error(ex);
		return new ResponseEntity<>(error, error.getHttpStatus());
	}
	
	@ExceptionHandler(UserIsInactiveException.class)
	public final ResponseEntity<Object> handleAllExceptions(UserIsInactiveException ex, WebRequest request) {
		ResponseDTO error = new ResponseDTO("User is inactive", HttpStatus.BAD_REQUEST, null);
		logger.error(ex);
		return new ResponseEntity<>(error, error.getHttpStatus());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleAllExceptions(ResourceNotFoundException ex, WebRequest request) {
		ResponseDTO error = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		logger.error(ex);
		return new ResponseEntity<>(error, error.getHttpStatus());
	}
}
