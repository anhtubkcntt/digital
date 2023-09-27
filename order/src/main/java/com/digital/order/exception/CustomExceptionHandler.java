package com.digital.order.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<Object> handleValidateOrderException(BusinessException ex) {
    log.debug(ex.getClass().getName());
    ApiError error = new ApiError();
    error.setError(ex.getHttpStatus().getReasonPhrase());
    error.setMessage(ex.getMessage());
    error.setStatus(String.valueOf(ex.getHttpStatus().value()));
    return ResponseEntity.status(ex.getHttpStatus()).body(error);
  }

  @Override
  protected ResponseEntity<Object>  handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    ApiError error = new ApiError();
    error.setError(status.getReasonPhrase());
    error.setMessage(errors.toString());
    error.setStatus(String.valueOf(status.value()));

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      final MethodArgumentTypeMismatchException ex, final WebRequest request) {
    logger.debug(ex.getClass().getName());
    String name = ex.getName();
    Class<?> theClass = ex.getRequiredType();
    String required = null != theClass ? theClass.getName() : null;
    if (!StringUtils.hasText(required)) {
      required = "unknown";
    }
    final String message = name + " should be of type " + required;

    ApiError error = new ApiError();
    error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
    error.setMessage(message);
    error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
