package com.internshipmanagementsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  Validation Errors (Missing Fields)
   @ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<?> handleDuplicate(DataIntegrityViolationException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 400);

    String message = ex.getMostSpecificCause().getMessage().toLowerCase();

    if (message.contains("email")) {
        response.put("message", "Email already exists");
    } else if (message.contains("title")) {
        response.put("message", "Internship with this title already exists");
    } else if (message.contains("phone")) {
        response.put("message", "Phone number already exists");
    } else {
        response.put("message", "Duplicate entry - record already exists");
    }

    return ResponseEntity.badRequest().body(response);
}
}