package com.olta.fundManager.TransactionService.Exception;

import com.olta.fundManager.TransactionService.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }

    @ExceptionHandler(TransactionServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(TransactionServiceException ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message(ex.getMessage())
                .success(false).build(), HttpStatus.BAD_REQUEST);
    }


    // Add additional exception handling methods as needed

    // Example of handling a specific type of exception
    /*@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Resource not found: " + e.getMessage());
    }*/
}
