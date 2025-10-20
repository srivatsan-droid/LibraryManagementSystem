package com.libraryManagementSystem.codes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "borrow_records")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"user", "book"})
public class BorrowRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // NOT List<User> - just ONE user

    @NotNull(message = "Book is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;  // NOT List<Book> - just ONE book

    @NotNull(message = "Borrow date is required")
    @Column(nullable = false)
    private LocalDate borrowDate;

    @NotNull(message = "Due date is required")
    @Column(nullable = false)
    private LocalDate dueDate;

    @Column
    private LocalDate returnDate;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private com.libraryManagementSystem.codes.entity.BorrowStatus status;

    @Column(precision = 10, scale = 2)
    private BigDecimal fine = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal finePerDay = new BigDecimal("5.00");

    @Column(length = 500)
    private String notes;

    @Column(nullable = false)
    private Integer renewalCount = 0;
}