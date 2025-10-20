package com.libraryManagementSystem.codes.controller;

import com.libraryManagementSystem.codes.dto.BookDTO;
import com.libraryManagementSystem.codes.entity.Book;
import com.libraryManagementSystem.codes.entity.BookCategory;
import com.libraryManagementSystem.codes.mapper.BookMapper;
import com.libraryManagementSystem.codes.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api1/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ✅ Get all books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks()
                .stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // ✅ Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        return new ResponseEntity<>(BookMapper.toDto(book), HttpStatus.OK);
    }

    // ✅ Search by title
    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO>> getBookByTitle(@RequestParam String title) {
        List<BookDTO> books = bookService.searchBookByTitle(title)
                .stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // ✅ Search by author
    @GetMapping("/search/author")
    public ResponseEntity<List<BookDTO>> getBookByAuthor(@RequestParam String author) {
        List<BookDTO> books = bookService.getBooksByAuthor(author)
                .stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // ✅ Search by category
    @GetMapping("/search/category")
    public ResponseEntity<List<BookDTO>> getBookByCategory(@RequestParam BookCategory category) {
        List<BookDTO> books = bookService.getBookByCategory(category)
                .stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // ✅ Create book
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(BookMapper.toDto(createdBook), HttpStatus.CREATED);
    }

    // ✅ Update book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(BookMapper.toDto(updatedBook), HttpStatus.OK);
    }

    // ✅ Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Deleted Book Successfully", HttpStatus.NO_CONTENT);
    }
}
