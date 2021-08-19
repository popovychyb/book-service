package com.example.book.service.exception;

public class BookNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Book is not found!";

    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
