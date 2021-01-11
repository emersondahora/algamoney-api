package com.emersonhora.algamoney.api.services.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emersonhora.algamoney.api.resource.exceptions.StandardError;

@ControllerAdvice
public class ServiceExceptionHandler {
	@Autowired
	MessageSource messageSource;
		
	@ExceptionHandler(PersonInaciveException.class)
	public ResponseEntity<StandardError> handlerPersonInaciveException(PersonInaciveException e, HttpServletRequest request) {
		String error = messageSource.getMessage("person.is-inactive", null, LocaleContextHolder.getLocale());
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ExceptionUtils.getRootCause(e).getMessage(), request.getRequestURI());
		
		err.getErrors().add(messageSource.getMessage("person.is-inactive", null, LocaleContextHolder.getLocale()));
		return ResponseEntity.status(status).body(err);
	}
		
}
