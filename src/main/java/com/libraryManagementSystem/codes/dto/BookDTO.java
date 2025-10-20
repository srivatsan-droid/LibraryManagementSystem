package com.libraryManagementSystem.codes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    private String isbn;

    private String title;

    private String author;

    private String publisher;

    private Integer publicationYear;

    private String edition;

    private Integer pages;

    private com.libraryManagementSystem.codes.entity.BookCategory category;

    private Integer totalCopies = 1;

    private Integer availableCopies = 1;

    private String shelfLocation;

    private BigDecimal price;

}
