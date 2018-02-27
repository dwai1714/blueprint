package com.corvesta.keyspring.blueprint.exception;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ItemControllerAdvice extends ResponseEntityExceptionHandler {

	// This will get invoked if the user tries to get a item that is not in the DB
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<VndErrors> notFoundException(final ItemNotFoundException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getItemtId().toString());
	}

	private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus,
			final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
	}

}
