package com.example.book.service.service.impl;

import com.example.book.service.dto.BookDto;
import com.example.book.service.model.Book;
import com.example.book.service.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    public static final long TEST_ID = 1L;
    public static final String TEST_TITLE = "Test Title";
    public static final String TEST_AUTHOR = "Test Author";
    public static final long TEST_PRICE_USD = 1L;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void getBookTest() {
        Book book = createBook();
        when(bookRepository.findById(TEST_ID)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getBook(TEST_ID);
        assertEquals(bookDto.getId(), book.getId());
    }

    private Book createBook() {
        Book book = new Book();
        book.setId(TEST_ID);
        book.setTitle(TEST_TITLE);
        book.setAuthor(TEST_AUTHOR);
        book.setPriceUsd(TEST_PRICE_USD);
        return book;
    }
}