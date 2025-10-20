package com.libraryManagementSystem.codes.service;

import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.BorrowRecord;
import com.libraryManagementSystem.codes.entity.BorrowStatus;
import com.libraryManagementSystem.codes.entity.User;
import com.libraryManagementSystem.codes.repository.BookRepository;
import com.libraryManagementSystem.codes.repository.BorrowRecordRepository;
import com.libraryManagementSystem.codes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    //Get Logic where we need to return all the Borrowed Records
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }
    //Get Record by Id
    public Optional<BorrowRecord> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }
    //Get Borrowed Records by user
    public List<BorrowRecord> getBorrowRecordsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        return borrowRecordRepository.findByUser(user);
    }
    //Delete Borrowed Record
    public void deleteBorrowRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }
    public BorrowRecord borrowRecord(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not found"));
        if(book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies lft for this book");
        }
        BorrowRecord borrowRecord = BorrowRecord.builder()
                .user(user)
                .book(book)
                .borrowDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .status(BorrowStatus.ACTIVE)
                .fine(null)
                .renewalCount(0)
                .build();
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return borrowRecordRepository.save(borrowRecord);
    }
    //Return a book
    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new RuntimeException("Borrow record not found"));
        if(record.getStatus() == BorrowStatus.RETURNED) {
            throw new RuntimeException("Book already returned");
        }
        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowStatus.RETURNED);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return borrowRecordRepository.save(record);
    }
}
