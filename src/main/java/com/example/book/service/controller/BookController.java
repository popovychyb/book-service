package com.example.book.service.controller;

import com.example.book.service.controller.assembler.BookAssembler;
import com.example.book.service.controller.model.BookModel;
import com.example.book.service.dto.BookDto;
import com.example.book.service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookAssembler bookAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public BookModel getBook(@PathVariable Long id) {
        BookDto book = bookService.getBook(id);
        return bookAssembler.toModel(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookModel createBook(@Valid @RequestBody BookDto bookDto) {
        BookDto book = bookService.createBook(bookDto);
        return bookAssembler.toModel(book);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public BookModel updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto book = bookService.updateBook(id, bookDto);
        return bookAssembler.toModel(book);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
