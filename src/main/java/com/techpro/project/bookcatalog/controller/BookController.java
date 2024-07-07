package com.techpro.project.bookcatalog.controller;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/")
public class BookController {
  @Autowired
  BookService bookService;

  // #region Get books/book
  @GetMapping("books")
  public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
    return bookService.getAllBooks(title);
  }

  @GetMapping("books/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    return bookService.getBookById(id);
  }
  // #endregion

  // #region Create books
  @PostMapping("book")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    return bookService.createBook(book);
  }

  @PostMapping("books")
  public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> books) {
    return bookService.createBooks(books);
  }
  // #endregion

  // #region Update book/books
  @PutMapping("books/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    return bookService.updateBook(id, book);
  }
  // #endregion

  // #region Delete book/books
  @DeleteMapping("books/{id}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
    return bookService.deleteBook(id);
  }

  @DeleteMapping("books")
  public ResponseEntity<HttpStatus> deleteAllBooks() {
    return bookService.deleteAllBooks();
  }
  // #endregion

  // #region Search book/books
  @GetMapping("books/published")
  public ResponseEntity<List<Book>> findByPublished(@RequestParam boolean status) {
    return bookService.findByPublished(status);
  }

  @GetMapping("books/author")
  public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
    return bookService.findByAuthor(author);
  }

  @GetMapping("books/title")
  public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
    return bookService.findByTitle(title);
  }

  @GetMapping("books/genre")
  public ResponseEntity<List<Book>> findByGenre(@RequestParam String genre) {
    return bookService.findByGenre(genre);
  }
  // #endregion
}
