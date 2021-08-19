package com.example.book.service.controller.model;

import com.example.book.service.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookModel extends RepresentationModel<BookModel> {

    @JsonUnwrapped
    private BookDto bookDto;

}
