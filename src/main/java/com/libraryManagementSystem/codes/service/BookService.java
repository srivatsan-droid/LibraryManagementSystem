package com.libraryManagementSystem.codes.service;

import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.BookCategory;
import com.libraryManagementSystem.codes.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Get all Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //Get one Book by Id
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    //Add a new Book
    public Book createBook(Book book) {
        if(bookRepository.findByIsbn(book.getIsbn()) != null) {
            throw new RuntimeException("Already ISBN ID with the book Exists");
        }
        return bookRepository.save(book);
    }
    //Updating the Existing Book
    public Book updateBook(Long id, Book updateBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setIsbn(updateBook.getIsbn());
                    existingBook.setTitle(updateBook.getTitle());
                    existingBook.setAuthor(updateBook.getAuthor());
                    existingBook.setPublisher(updateBook.getPublisher());
                    existingBook.setPublicationYear(updateBook.getPublicationYear());
                    existingBook.setEdition(updateBook.getEdition());
                    existingBook.setLanguage(updateBook.getLanguage());
                    existingBook.setPages(updateBook.getPages());
                    existingBook.setCategory(updateBook.getCategory());
                    existingBook.setDescription(updateBook.getDescription());
                    existingBook.setCoverImageUrl(updateBook.getCoverImageUrl());
                    existingBook.setTotalCopies(updateBook.getTotalCopies());
                    existingBook.setAvailableCopies(updateBook.getAvailableCopies());
                    existingBook.setShelfLocation(updateBook.getShelfLocation());
                    existingBook.setPrice(updateBook.getPrice());
                    existingBook.setBorrowRecords(updateBook.getBorrowRecords());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with Relevant not found"));
    }
    //Delete an Book After checking if its borrowed
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    //Search for the book by title
    public List<Book> searchBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    //GET Books by Author
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }
    //Get Books by Category
    public List<Book> getBookByCategory(BookCategory category) {
        return bookRepository.findByCategory(category);
    }

}
