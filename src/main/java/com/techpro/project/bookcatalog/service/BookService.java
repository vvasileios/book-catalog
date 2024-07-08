package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.model.BookInfo;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * This interface defines the methods that the BookService class must implement.
 * It provides the basic CRUD operations for the Book entity.
 */
public interface BookService {
  ResponseEntity<List<BookInfo>> getAllBooks();

  ResponseEntity<Book> getBookById(Long id);

  ResponseEntity<Book> createBook(Book book);

  ResponseEntity<List<Book>> createBooks(List<Book> books);

  ResponseEntity<Book> updateBook(Long id, Book book);

  ResponseEntity<HttpStatus> deleteBook(Long id);

  ResponseEntity<HttpStatus> deleteAllBooks();

  ResponseEntity<List<Book>> findByPublished(boolean status);

  ResponseEntity<List<Book>> findByAuthor(String author);

  ResponseEntity<List<Book>> findByTitle(String title);

  ResponseEntity<List<Book>> findByGenre(String genre);
}
