package com.techpro.project.bookcatalog.controller;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping("/books")
  public List<Book> getAllBooks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return bookService.getAllBooks(page, size);
  }

  @GetMapping("/book/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookService.getBookById(id);
    return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // @PostMapping("/book/add")
  // @Secured("ROLE_ADMIN")
  // public Book addBook(@RequestBody Book book) {
  // return bookService.addBook(book);
  // }

  // @PutMapping("/book/{id}")
  // @Secured("ROLE_ADMIN")
  // public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody
  // Book bookDetails) {
  // Book updatedBook = bookService.updateBook(id, bookDetails);
  // if (updatedBook != null) {
  // return ResponseEntity.ok(updatedBook);
  // } else {
  // return ResponseEntity.notFound().build();
  // }
  // }

  // @DeleteMapping("/book/{id}")
  // @Secured("ROLE_ADMIN")
  // public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
  // bookService.deleteBook(id);
  // return ResponseEntity.noContent().build();
  // }

  // @GetMapping("/book/search")
  // public List<Book> searchBooks(@RequestParam(required = false) String title,
  // @RequestParam(required = false) String author) {
  // return bookService.searchBooks(title, author);
  // }
}
