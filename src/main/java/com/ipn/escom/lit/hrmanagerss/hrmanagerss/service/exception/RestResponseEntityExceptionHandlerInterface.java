package com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface RestResponseEntityExceptionHandlerInterface {
  ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
    HttpStatus status, WebRequest request);

}
