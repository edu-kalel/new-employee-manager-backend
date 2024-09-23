package com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler implements RestResponseEntityExceptionHandlerInterface {

  @ExceptionHandler(value = {UsernameNotFoundException.class})
  protected ResponseEntity<Object> handleUsernameNotFoundConflict(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
      new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {BadCredentialsException.class})
  protected ResponseEntity<Object> handleBadCredentialsConflict(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
      new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(value = {EntityExistsException.class})
  protected ResponseEntity<Object> handleEntityExistsConflict(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
      new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  protected ResponseEntity<Object> handleEntityNotFoundConflict(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
      new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @Override
  public ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
    HttpStatus status, WebRequest request) {
    ApiError error = new ApiError(status.value(), exception.getMessage());
    return super.handleExceptionInternal(exception, error, headers, status, request);
  }
}
