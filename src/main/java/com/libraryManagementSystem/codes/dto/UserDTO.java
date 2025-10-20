package com.libraryManagementSystem.codes.dto;

import com.libraryManagementSystem.codes.entity.BorrowStatus;
import com.libraryManagementSystem.codes.entity.Role;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private Role role;

    private LocalDate membershipDate;

    // Optional flags (include only if you plan admin-level management)
    private boolean enabled;

    private boolean accountNonLocked;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BorrowRecordDTO {

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
}
