package com.techpro.project.bookcatalog.controller;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.repository.BookRepository;
import com.techpro.project.bookcatalog.exception.ResourceNotFoundException;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  BookRepository bookRepository;

  // #region Get books/book
  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
    try {
      List<Book> books = new ArrayList<Book>();

      if (title == null)
        bookRepository.findAll().forEach(books::add);
      else
        bookRepository.findByTitleContainingIgnoreCase(title).forEach(books::add);

      if (books.isEmpty()) {
        throw new ResourceNotFoundException("No books found. Please consider adding some books!");
      }

      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books", e);
    }
  }

  @GetMapping("books/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
    } else {
      throw new ResourceNotFoundException("Book not found with id: " + id);
    }
  }
  // #endregion

  // #region Create books
  @PostMapping("book")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    try {
      Book _book = bookRepository
          .save(new Book(book.getTitle(), book.getAuthor(), book.getSummary(), book.isPublished(),
              book.getPublicationYear(), book.getGenre()));
      return new ResponseEntity<>(_book, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException("Error creating book", e);
    }
  }

  @PostMapping("books")
  public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> books) {
    try {
      List<Book> _books = bookRepository.saveAll(books);
      return new ResponseEntity<>(_books, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException("Error creating books", e);
    }
  }
  // #endregion

  // #region Update book/books
  @PutMapping("books/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      Book _book = bookData.get();
      _book.setTitle(book.getTitle());
      _book.setAuthor(book.getAuthor());
      _book.setSummary(book.getSummary());
      _book.setPublished(book.isPublished());
      _book.setPublicationYear(book.getPublicationYear());
      _book.setGenre(book.getGenre());
      return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
    } else {
      throw new ResourceNotFoundException("Book not found with id: " + id);
    }
  }
  // #endregion

  // #region Delete book/books
  @DeleteMapping("books/{id}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
    try {
      bookRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("books")
  public ResponseEntity<HttpStatus> deleteAllBooks() {
    try {
      bookRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  // #endregion

  // #region Search book/books
  @GetMapping("books/published")
  public ResponseEntity<List<Book>> findByPublished(@RequestParam boolean status) {
    try {
      List<Book> books = bookRepository.findByPublished(status);

      if (books.isEmpty()) {
        throw new ResourceNotFoundException("No books found with published status: " + status);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with published status: " + status, e);
    }
  }

  @GetMapping("books/author")
  public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
    try {
      List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);

      if (books.isEmpty()) {
        throw new ResourceNotFoundException("No books found with author: " + author);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with author: " + author, e);
    }
  }

  @GetMapping("books/title")
  public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
    try {
      List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);

      if (books.isEmpty()) {
        throw new ResourceNotFoundException("No books found with title: " + title);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with title: " + title, e);
    }
  }

  @GetMapping("books/genre")
  public ResponseEntity<List<Book>> findByGenre(@RequestParam String genre) {
    try {
      List<Book> books = bookRepository.findByGenreContainingIgnoreCase(genre);

      if (books.isEmpty()) {
        throw new ResourceNotFoundException("No books found with genre: " + genre);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with genre: " + genre, e);
    }
  }
  // #endregion
}
