package com.example.book.service.service;

import com.example.book.service.dto.BookDto;

public interface BookService {
    BookDto getBook(Long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(Long id, BookDto bookDto);

    void deleteBook(Long id);
}
