package com.nbcampif.ifstagram.global.handler;

<<<<<<< HEAD
import com.nbcampif.ifstagram.global.dto.CommonResponse;
=======
import com.nbcampif.ifstagram.global.exception.NotFoundUserException;
import com.nbcampif.ifstagram.global.exception.PermissionNotException;
import com.nbcampif.ifstagram.global.response.ErrorResponse;
import java.util.concurrent.RejectedExecutionException;
>>>>>>> e631d145fc0deebfb8f965fda656f46bc6548ab1
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
<<<<<<< HEAD
    public ResponseEntity<CommonResponse<?>> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        CommonResponse<?> commonResponse = new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                commonResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({RejectedExecutionException.class})
    public ResponseEntity<CommonResponse<?>> RejectedExecutionExceptionHandler(RejectedExecutionException ex) {
        CommonResponse<?> commonResponse = new CommonResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                commonResponse,
                HttpStatus.NOT_FOUND
        );
=======
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
>>>>>>> e631d145fc0deebfb8f965fda656f46bc6548ab1
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
