package com.shiv.countwords.advice;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.shiv.countwords.model.ApiError;
import com.shiv.countwords.model.InvalidToken;
import jakarta.validation.ConstraintViolationException;

/**
 * ValidationExceptionHandler is a global exception handler for handling validation errors and other
 * common exceptions in the application. It uses Spring's @ControllerAdvice to handle exceptions
 * globally.
 * 
 * @author Shivpal Chouhan
 */
@ControllerAdvice
public class ValidationExceptionHandler
{

  // Handle @Valid on @RequestBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
  {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    ApiError apiError = new ApiError("Validation failed", HttpStatus.BAD_REQUEST, errors);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  // Handle @Valid/@Validated on path params or query params
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex)
  {
    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations().forEach(
        violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
    ApiError apiError = new ApiError("Constraint violation", HttpStatus.BAD_REQUEST, errors);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidToken.class)
  public ResponseEntity<ApiError> handleInvalidToken(InvalidToken ex)
  {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  // Handle multiple common exceptions (like MissingRequestHeaderException, etc.)
  @ExceptionHandler({MissingRequestHeaderException.class})
  public ResponseEntity<ApiError> handleCommonExceptions(Exception ex)
  {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  // Catch-all fallback
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleAllExceptions(Exception ex)
  {
    ex.printStackTrace();
    ApiError apiError = new ApiError("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
