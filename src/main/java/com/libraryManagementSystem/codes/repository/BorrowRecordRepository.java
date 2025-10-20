package com.libraryManagementSystem.codes.repository;

import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.BorrowRecord;
import com.libraryManagementSystem.codes.entity.BorrowStatus;
import com.libraryManagementSystem.codes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    //Find all borrow records by user
    List<BorrowRecord> findByUser(User user);

    //Find all borrow records for a particular book
    List<BorrowRecord> findByBook(Book book);

    //Find all Overdue records
    List<BorrowRecord> findByStatus(BorrowStatus status);

    //Find Records by due date before Today
    List<BorrowRecord> findByDueDateBefore(LocalDate date);

    //Find all active borrowing of a user
    List<BorrowRecord> findByUserAndStatus(User user,BorrowStatus status);
}
