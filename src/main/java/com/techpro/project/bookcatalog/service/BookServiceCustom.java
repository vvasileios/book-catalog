package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.model.BookInfo;
import com.techpro.project.bookcatalog.repository.BookRepository;
import com.techpro.project.bookcatalog.system.result.Result;
import com.techpro.project.bookcatalog.system.result.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class implements the BookService interface and provides the methods to
 * handle CRUD operations for the Book entity.
 */
@Service
public class BookServiceCustom implements BookService {
  @Autowired
  BookRepository bookRepository;

  @Override
  public Result getAllBooks() {
    try {
      List<Book> books = bookRepository.findAll();

      if (books.isEmpty()) {
        return new Result(false, StatusCode.NOT_FOUND, "No books found. Please consider adding some books!", null);
      }

      List<BookInfo> bookInfos = books.stream()
          .map(book -> new BookInfo(book.getId(), book.getTitle(), book.getAuthor()))
          .collect(Collectors.toList());

      return new Result(true, StatusCode.SUCCESS, "Books retrieved successfully.", bookInfos);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Error retrieving books.", null);
    }
  }

  @Override
  public Result getBookById(Long id) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      return new Result(true, StatusCode.SUCCESS, "Books retrieved successfully.", bookData);
    } else {
      return new Result(false, StatusCode.NOT_FOUND, "Book not found with id: " + id, null);
    }
  }

  @Override
  public Result createBook(Book book) {
    try {
      Optional<Book> existingBook = bookRepository.findByTitleAndAuthorContainingIgnoreCase(book.getTitle(),
          book.getAuthor());
      if (existingBook.isPresent()) {
        return new Result(false, StatusCode.INVALID_ARGUMENT, "Book already exists", existingBook);
      }
      Book _book = bookRepository
          .save(new Book(book.getTitle(), book.getAuthor(), book.getSummary(), book.isPublished(),
              book.getPublicationYear(), book.getGenre()));
      return new Result(true, StatusCode.SUCCESS, "Book saved successfully", _book);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Error creating book", null);
    }
  }

  @Override
  public Result createBooks(List<Book> books) {
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
      return new Result(true, StatusCode.SUCCESS, "Books saved successfully", savedBooks);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Error creating books", null);
    }
  }

  @Override
  public Result updateBook(Long id, Book book) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      Book _book = bookData.get();
      _book.setTitle(book.getTitle());
      _book.setAuthor(book.getAuthor());
      _book.setSummary(book.getSummary());
      _book.setPublished(book.isPublished());
      _book.setPublicationYear(book.getPublicationYear());
      _book.setGenre(book.getGenre());
      return new Result(true, StatusCode.SUCCESS, "Book updated successfully", bookRepository.save(_book));
    } else {
      return new Result(false, StatusCode.NOT_FOUND, "Book not found with id: " + id, null);
    }
  }

  @Override
  public Result deleteBook(Long id) {
    try {
      bookRepository.deleteById(id);
      return new Result(true, StatusCode.SUCCESS, "Book deleted successfully", null);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Book not found with id: " + id, null);
    }
  }

  @Override
  public Result deleteAllBooks() {
    try {
      bookRepository.deleteAll();
      return new Result(true, StatusCode.SUCCESS, "All books deleted successfully", null);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "No books found. Please consider adding some books!",
          null);
    }
  }

  @Override
  public Result findByPublished(boolean status) {
    try {
      List<Book> books = bookRepository.findByPublished(status);

      if (books.isEmpty()) {
        return new Result(false, StatusCode.NOT_FOUND, "No books found with published status: " + status, null);
      }
      return new Result(true, StatusCode.SUCCESS, "Books by published status: " + status + ", retrieved successfully.",
          books);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR,
          "Error retrieving books with published status: " + status,
          null);
    }
  }

  @Override
  public Result findByAuthor(String author) {
    try {
      List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);

      if (books.isEmpty()) {
        return new Result(false, StatusCode.NOT_FOUND, "No books found with author: " + author, null);
      }
      return new Result(true, StatusCode.SUCCESS, "Books by author retrieved successfully.", books);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR,
          "Error retrieving books with author: " + author,
          null);
    }
  }

  @Override
  public Result findByTitle(String title) {
    try {
      List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);

      if (books.isEmpty()) {
        return new Result(false, StatusCode.NOT_FOUND, "No books found with title: " + title, null);
      }
      return new Result(true, StatusCode.SUCCESS, "Books by title retrieved successfully.", books);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR,
          "Error retrieving books with title: " + title,
          null);
    }
  }

  @Override
  public Result findByGenre(String genre) {
    try {
      List<Book> books = bookRepository.findByGenreContainingIgnoreCase(genre);

      if (books.isEmpty()) {
        return new Result(false, StatusCode.NOT_FOUND, "No books found with genre: " + genre, null);
      }
      return new Result(true, StatusCode.SUCCESS, "Books by genre retrieved successfully.", books);
    } catch (Exception e) {
      return new Result(false, StatusCode.INTERNAL_SERVER_ERROR,
          "Error retrieving books with genre: " + genre,
          null);
    }
  }
}
