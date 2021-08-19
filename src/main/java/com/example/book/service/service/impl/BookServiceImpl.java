package com.example.book.service.service.impl;

import com.example.book.service.dto.BookDto;
import com.example.book.service.exception.BookNotFoundException;
import com.example.book.service.model.Book;
import com.example.book.service.repository.BookRepository;
import com.example.book.service.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto getBook(Long id) {
        log.info("Get book by id {}", id);
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        Book book = bookRepository.getById(id);
        return mapBookToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        log.info("Create book: {}", bookDto);
        Book book = mapBookDtoToBook(bookDto);
        book = bookRepository.save(book);
        return mapBookToBookDto(book);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        log.info("Update book by id {}", id);
        Book book = mapBookDtoToBook(bookDto);
        book = bookRepository.save(book);
        return mapBookToBookDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        log.info("Delete book by id {}", id);
        bookRepository.deleteById(id);
    }

    private BookDto mapBookToBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .priceUsd(book.getPriceUsd())
                .priceEur(book.getPriceEur())
                .priceUah(book.getPriceUah())
                .build();
    }

    private Book mapBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .priceUsd(bookDto.getPriceUsd())
                .priceEur(bookDto.getPriceEur())
                .priceUah(bookDto.getPriceUah())
                .build();
    }
}
