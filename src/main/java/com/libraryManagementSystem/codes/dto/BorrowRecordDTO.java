package com.libraryManagementSystem.codes.dto;

import com.libraryManagementSystem.codes.entity.BorrowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecordDTO {

    private Long id;

    private Long userId;       // instead of full User object
    private String username;   // optional, for convenience

    private Long bookId;       // instead of full Book object
    private String bookTitle;  // optional, for convenience

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private BorrowStatus status;
    private BigDecimal fine;
    private BigDecimal finePerDay;
    private String notes;
    private Integer renewalCount;
}

