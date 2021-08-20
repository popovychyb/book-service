package com.example.book.service.service.impl;

import com.example.book.service.dto.BookDto;
import com.example.book.service.exception.BookNotFoundException;
import com.example.book.service.model.Book;
import com.example.book.service.repository.BookRepository;
import com.example.book.service.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto getBook(Long id) {
        log.info("Get book by id {}", id);
        return mapBookToBookDto(bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new));
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        log.info("Create book: {}", bookDto);
        addCurrencies(bookDto);
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

    private void addCurrencies(BookDto bookDto) {
        String url = "http://data.fixer.io/api/latest?access_key=25e0c9e92ecc6198b407c708e43f1a43&symbols=USD,EUR,UAH";
        String rawJson = new RestTemplate().getForObject(url, String.class);
        JSONObject rates = new JSONObject(rawJson).getJSONObject("rates");

        bookDto.setPriceUsd((long) (rates.getDouble("USD") * bookDto.getPriceEur()));
        bookDto.setPriceUah((long) (rates.getDouble("UAH") * bookDto.getPriceEur()));
    }
}
