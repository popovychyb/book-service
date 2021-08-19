package com.example.book.service.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class BookDto {

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private int priceUsd;
    private int priceEur;
    private int priceUah;

}
