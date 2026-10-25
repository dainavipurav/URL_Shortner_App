package com.learning.springboot.urlshortner.exceptions;

import com.learning.springboot.urlshortner.Dto.UrlError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UrlExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UrlError> handleUrlNotFoundException(UrlNotFoundException exception){
        return new ResponseEntity<>( UrlError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .error(exception.getMessage())
                .message("No Data Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UrlError> handleGenericException(Exception exception){
        return new ResponseEntity<>(UrlError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .message("Internal Server Error")
                .timestamp(System.currentTimeMillis())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}
