package com.example.priceservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Bean Validation failures (e.g., amount not digits)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
    String msg = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", msg));
  }

  // Unsupported currency: Enum.valueOf throws IllegalArgumentException
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
  }
}
