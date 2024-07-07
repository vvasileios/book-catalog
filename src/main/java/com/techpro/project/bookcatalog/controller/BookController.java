package com.techpro.project.bookcatalog.controller;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@RequestMapping("/books")
public class BookController {
  @Autowired
  BookService bookService;

  // #region Get books/book
  @Tag(name = "Get", description = "Get books")
  @Operation(summary = "Get all books", description = "Get all books or filter by title")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    return bookService.getAllBooks();
  }

  @Tag(name = "Get")
  @Operation(summary = "Get book by id", description = "Get book by providing a specific id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "Book not found with id", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    return bookService.getBookById(id);
  }
  // #endregion

  // #region Create books
  @Tag(name = "Post", description = "Create books")
  @Operation(summary = "Create book", description = "Create a single new book")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Book created", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "400", description = "Error creating book", content = @Content)
  })
  @PostMapping("/add")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    return bookService.createBook(book);
  }

  @Tag(name = "Post")
  @Operation(summary = "Create books", description = "Create multiple new books")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Books created", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "400", description = "Error creating books", content = @Content)
  })
  @PostMapping
  public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> books) {
    return bookService.createBooks(books);
  }
  // #endregion

  // #region Update book/books
  @Tag(name = "Put", description = "Update book")
  @Operation(summary = "Update book", description = "Update a book by providing a specific id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Book updated", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "Book not found with id", content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    return bookService.updateBook(id, book);
  }
  // #endregion

  // #region Delete book/books
  @Tag(name = "Delete", description = "Delete books")
  @Operation(summary = "Delete book", description = "Delete a book by providing a specific id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Book deleted", content = @Content),
      @ApiResponse(responseCode = "404", description = "Book not found with id", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
    return bookService.deleteBook(id);
  }

  @Tag(name = "Delete")
  @Operation(summary = "Delete books", description = "Deletes all books from the database")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "All books deleted", content = @Content),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @DeleteMapping
  public ResponseEntity<HttpStatus> deleteAllBooks() {
    return bookService.deleteAllBooks();
  }
  // #endregion

  // #region Search book/books
  @Tag(name = "Get")
  @Operation(summary = "Get books by status", description = "Get books by published status")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @GetMapping("/published")
  public ResponseEntity<List<Book>> findByPublished(@RequestParam boolean status) {
    return bookService.findByPublished(status);
  }

  @Tag(name = "Get")
  @Operation(summary = "Get books by author", description = "Get books by author")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @GetMapping("/author")
  public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
    return bookService.findByAuthor(author);
  }

  @Tag(name = "Get")
  @Operation(summary = "Get books title", description = "Get books by title")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @GetMapping("/title")
  public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
    return bookService.findByTitle(title);
  }

  @Tag(name = "Get")
  @Operation(summary = "Get books by genre", description = "Get books by genre")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "404", description = "No books found. Please consider adding some books!", content = @Content)
  })
  @GetMapping("/genre")
  public ResponseEntity<List<Book>> findByGenre(@RequestParam String genre) {
    return bookService.findByGenre(genre);
  }
  // #endregion
}
