package com.libraryManagementSystem.codes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
@EqualsAndHashCode(callSuper = true, exclude = "borrowRecords")
@ToString(exclude = "borrowRecords")
public class Book extends BaseEntity {

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 3, max = 100, message = "Author name must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    private String author;

    @Size(max = 100, message = "Publisher name should not exceed 100 characters")
    @Column(length = 100)
    private String publisher;

    @Min(value = 1950, message = "Publication year must be at least 1950")
    @Max(value = 2025, message = "Publication year cannot be in the future")
    private Integer publicationYear;

    @Size(max = 50, message = "Edition should not exceed 50 characters")
    @Column(length = 50)
    private String edition;

    @Size(max = 30, message = "Language should not exceed 30 characters")
    @Column(length = 30)
    private String language = "English";

    @Min(value = 1, message = "Pages must be at least 1")
    private Integer pages;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private com.libraryManagementSystem.codes.entity.BookCategory category;

    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    @Column(length = 1000)
    private String description;

    @Size(max = 255, message = "Cover image URL should not exceed 255 characters")
    @Column(length = 255)
    private String coverImageUrl;

    @NotNull(message = "Total copies is required")
    @Min(value = 1, message = "Total copies must be at least 1")
    @Column(nullable = false)
    private Integer totalCopies = 1;

    @NotNull(message = "Available copies is required")
    @Min(value = 0, message = "Available copies cannot be negative")
    @Column(nullable = false)
    private Integer availableCopies = 1;

    @Size(max = 50, message = "Shelf location should not exceed 50 characters")
    @Column(length = 50)
    private String shelfLocation;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
    private BigDecimal price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
}