package com.libraryManagementSystem.codes.repository;

import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //Find Book by isbn
    Optional<Book> findByIsbn(String isbn);

    //find books by author name
    List<Book> findByAuthorIgnoreCase(String author);

    //Find Books by Category
    List<Book> findByCategory(BookCategory category);

    //Search Books by title keyword
    List<Book> findByTitleContainingIgnoreCase(String title);

    //Find all Books that have available copies > 0
    List<Book> findByAvailableCopiesGreaterThan(Integer availableCopies);

}
