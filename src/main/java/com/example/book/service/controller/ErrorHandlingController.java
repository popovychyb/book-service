package com.example.book.service.controller;

import com.example.book.service.exception.BookNotFoundException;
import com.example.book.service.model.Error;
import com.example.book.service.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleBookNotFoundException(BookNotFoundException exception) {
        return new Error(exception.getMessage(), ErrorType.DATABASE_ERROR_TYPE, LocalDateTime.now());
    }

}
