package com.libraryManagementSystem.codes.mapper;

import com.libraryManagementSystem.codes.dto.BookDTO;
import com.libraryManagementSystem.codes.entity.Book;

public class BookMapper {

    public static BookDTO toDto(Book book) {
        if(book == null)
            return null;
        return BookDTO.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .edition(book.getEdition())
                .pages(book.getPages())
                .category(book.getCategory())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .shelfLocation(book.getShelfLocation())
                .price(book.getPrice())
                .build();
    }
    //Convert the DTO to Entity
    public static Book toEntity(BookDTO bookDto) {
        if(bookDto == null) return null;
        return Book.builder()
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .edition(bookDto.getEdition())
                .pages(bookDto.getPages())
                .category(bookDto.getCategory())
                .totalCopies(bookDto.getTotalCopies())
                .availableCopies(bookDto.getAvailableCopies())
                .shelfLocation(bookDto.getShelfLocation())
                .price(bookDto.getPrice())
                .build();
    }
}
