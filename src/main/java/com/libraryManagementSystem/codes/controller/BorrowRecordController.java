package com.libraryManagementSystem.codes.controller;


import com.libraryManagementSystem.codes.dto.BorrowRecordDTO;
import com.libraryManagementSystem.codes.entity.BorrowRecord;
import com.libraryManagementSystem.codes.mapper.BorrowRecordMapper;
import com.libraryManagementSystem.codes.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    // 1️⃣ Borrow a book
    @PostMapping("/issue")
    public ResponseEntity<BorrowRecordDTO> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        BorrowRecord record = borrowRecordService.borrowRecord(userId, bookId);
        return ResponseEntity.ok(BorrowRecordMapper.toDTO(record));
    }

    // 2️⃣ Return a book
    @PostMapping("/return")
    public ResponseEntity<BorrowRecordDTO> returnBook(
            @RequestParam Long bookId) {
        BorrowRecord record = borrowRecordService.returnBook(bookId);
        return ResponseEntity.ok(BorrowRecordMapper.toDTO(record));
    }

    // 3️⃣ Get all borrow records
    @GetMapping("/all")
    public ResponseEntity<List<BorrowRecordDTO>> getAllRecords() {
        List<BorrowRecordDTO> records = borrowRecordService.getAllBorrowRecords()
                .stream()
                .map(BorrowRecordMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(records);
    }

    // 4️⃣ Get user-specific records
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowRecordDTO>> getUserRecords(@PathVariable Long userId) {
        List<BorrowRecordDTO> records = borrowRecordService.getBorrowRecordsByUser(userId)
                .stream()
                .map(BorrowRecordMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(records);
    }
}

