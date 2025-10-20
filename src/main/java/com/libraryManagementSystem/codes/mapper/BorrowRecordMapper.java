package com.libraryManagementSystem.codes.mapper;

import com.libraryManagementSystem.codes.dto.BorrowRecordDTO;
import com.libraryManagementSystem.codes.entity.BorrowRecord;
import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.User;

public class BorrowRecordMapper {

    // ✅ Convert Entity → DTO
    public static BorrowRecordDTO toDTO(BorrowRecord record) {
        if (record == null) return null;

        return BorrowRecordDTO.builder()
                .id(record.getId())
                .userId(record.getUser() != null ? record.getUser().getId() : null)
                .username(record.getUser() != null ? record.getUser().getUsername() : null)
                .bookId(record.getBook() != null ? record.getBook().getId() : null)
                .bookTitle(record.getBook() != null ? record.getBook().getTitle() : null)
                .borrowDate(record.getBorrowDate())
                .dueDate(record.getDueDate())
                .returnDate(record.getReturnDate())
                .status(record.getStatus())
                .fine(record.getFine())
                .renewalCount(record.getRenewalCount())
                .build();
    }

    // ⚠️ DTO → Entity
    public static BorrowRecord toEntity(BorrowRecordDTO dto, User user, Book book) {
        if (dto == null) return null;

        return BorrowRecord.builder()
                .id(dto.getId())
                .user(user)       // fetch user from UserRepository by dto.getUserId()
                .book(book)       // fetch book from BookRepository by dto.getBookId()
                .borrowDate(dto.getBorrowDate())
                .dueDate(dto.getDueDate())
                .returnDate(dto.getReturnDate())
                .status(dto.getStatus())
                .fine(dto.getFine())
                .renewalCount(dto.getRenewalCount())
                .build();
    }
}

