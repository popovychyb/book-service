package com.example.book.service.controller.assembler;

import com.example.book.service.controller.BookController;
import com.example.book.service.controller.model.BookModel;
import com.example.book.service.dto.BookDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookAssembler extends RepresentationModelAssemblerSupport<BookDto, BookModel> {

    public BookAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(BookDto entity) {
        BookModel bookModel = new BookModel(entity);
        Link get = linkTo(methodOn(BookController.class).getBook(entity.getId())).withRel("get");
        Link create = linkTo(methodOn(BookController.class).getBook(entity.getId())).withRel("create");
        Link update = linkTo(methodOn(BookController.class).getBook(entity.getId())).withRel("update");
        Link delete = linkTo(methodOn(BookController.class).deleteBook(entity.getId())).withRel("delete");
        bookModel.add(get, create, update, delete);
        return bookModel;
    }
}
