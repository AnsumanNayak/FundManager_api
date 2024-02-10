package com.olta.fundManager.FundService.Exception;


import com.olta.fundManager.FundService.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FundServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(FundServiceException ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message(ex.getMessage())
                .success(false).build(), HttpStatus.BAD_REQUEST);
    }

    // Add more exception handlers as needed

    // For handling other exceptions, you can define additional @ExceptionHandler methods
    // For example:
    // @ExceptionHandler(OtherException.class)
    // public ResponseEntity<String> handleOtherException(OtherException ex) {
    //     return new ResponseEntity<>("Other Exception Occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    // Default handler for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
