package com.nbcampif.ifstagram.global.handler;

import com.nbcampif.ifstagram.global.exception.NotFoundUserException;
import com.nbcampif.ifstagram.global.exception.PermissionNotException;
import com.nbcampif.ifstagram.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({RejectedExecutionException.class})
    public ResponseEntity<ErrorResponse> RejectedExecutionExceptionHandler(RejectedExecutionException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorResponse> handleNotFountUserException(NotFoundUserException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler(PermissionNotException.class)
    public ResponseEntity<ErrorResponse> handlePermissionNotException(PermissionNotException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }
}
