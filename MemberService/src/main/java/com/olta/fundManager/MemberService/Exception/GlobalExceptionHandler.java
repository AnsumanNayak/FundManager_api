package com.olta.fundManager.MemberService.Exception;



import com.olta.fundManager.MemberService.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(MemberServiceException ex) {
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
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message(ex.getLocalizedMessage())
                .success(false).build(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
