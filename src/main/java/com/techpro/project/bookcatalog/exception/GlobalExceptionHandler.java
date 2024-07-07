package com.techpro.project.bookcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "Not Found");
    body.put("message",
        "The requested URL was not found on this server.");
    body.put("nextStep", "Check documentation at http://localhost:8080/");
    body.put("status", HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}
