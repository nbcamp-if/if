package com.nbcampif.ifstagram;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
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
    }
}