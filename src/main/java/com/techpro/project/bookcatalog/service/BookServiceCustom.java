package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.model.BookInfo;
import com.techpro.project.bookcatalog.repository.BookRepository;
import com.techpro.project.bookcatalog.exception.NoSuchBookExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BookServiceCustom implements BookService {
  @Autowired
  BookRepository bookRepository;

  @Override
  public ResponseEntity<List<BookInfo>> getAllBooks() {
    try {
      List<Book> books = bookRepository.findAll();

      if (books.isEmpty()) {
        throw new NoSuchBookExistException("No books found.");
      }

      List<BookInfo> bookInfos = books.stream()
          .map(book -> new BookInfo(book.getId(), book.getTitle(), book.getAuthor()))
          .collect(Collectors.toList());

      return new ResponseEntity<>(bookInfos, HttpStatus.OK);
    } catch (Exception e) {
      throw new NoSuchBookExistException("No books found. Please consider adding some books!");
    }
  }

  @Override
  public ResponseEntity<Book> getBookById(Long id) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
    } else {
      throw new NoSuchBookExistException("Book not found with id: " + id);
    }
  }

  @Override
  public ResponseEntity<Book> createBook(Book book) {
    try {
      Optional<Book> existingBook = bookRepository.findByTitleAndAuthorContainingIgnoreCase(book.getTitle(),
          book.getAuthor());
      if (existingBook.isPresent()) {
        throw new RuntimeException(
            "Book already exists with title: " + book.getTitle() + " and author: " + book.getAuthor());
      }
      Book _book = bookRepository
          .save(new Book(book.getTitle(), book.getAuthor(), book.getSummary(), book.isPublished(),
              book.getPublicationYear(), book.getGenre()));
      return new ResponseEntity<>(_book, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException("Error creating book", e);
    }
  }

  @Override
  public ResponseEntity<List<Book>> createBooks(List<Book> books) {
    try {
      List<Book> existingBooks = bookRepository.findAll();
      List<Book> newBooks = new ArrayList<>();

      for (Book book : books) {
        boolean exists = false;
        for (Book existingBook : existingBooks) {
          if (existingBook.getTitle().equalsIgnoreCase(book.getTitle())
              && existingBook.getAuthor().equalsIgnoreCase(book.getAuthor())) {
            exists = true;
            break;
          }
        }
        if (!exists) {
          newBooks.add(book);
        }
      }

      List<Book> savedBooks = bookRepository.saveAll(newBooks);
      return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException("Error creating books", e);
    }
  }

  @Override
  public ResponseEntity<Book> updateBook(Long id, Book book) {
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
      throw new NoSuchBookExistException("Book not found with id: " + id);
    }
  }

  @Override
  public ResponseEntity<HttpStatus> deleteBook(Long id) {
    try {
      bookRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      throw new NoSuchBookExistException("Book not found with id: " + id);
    }
  }

  @Override
  public ResponseEntity<HttpStatus> deleteAllBooks() {
    try {
      bookRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      throw new NoSuchBookExistException("No books found.");
    }
  }

  @Override
  public ResponseEntity<List<Book>> findByPublished(boolean status) {
    try {
      List<Book> books = bookRepository.findByPublished(status);

      if (books.isEmpty()) {
        throw new NoSuchBookExistException("No books found with published status: " + status);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with published status: " + status, e);
    }
  }

  @Override
  public ResponseEntity<List<Book>> findByAuthor(String author) {
    try {
      List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);

      if (books.isEmpty()) {
        throw new NoSuchBookExistException("No books found with author: " + author);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with author: " + author, e);
    }
  }

  @Override
  public ResponseEntity<List<Book>> findByTitle(String title) {
    try {
      List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);

      if (books.isEmpty()) {
        throw new NoSuchBookExistException("No books found with title: " + title);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with title: " + title, e);
    }
  }

  @Override
  public ResponseEntity<List<Book>> findByGenre(String genre) {
    try {
      List<Book> books = bookRepository.findByGenreContainingIgnoreCase(genre);

      if (books.isEmpty()) {
        throw new NoSuchBookExistException("No books found with genre: " + genre);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving books with genre: " + genre, e);
    }
  }
}
