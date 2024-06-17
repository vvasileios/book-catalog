package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  public List<Book> getAllBooks(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAll(pageable).getContent();
  }

  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public Book addBook(Book book) {
    return bookRepository.save(book);
  }

  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

  public List<Book> getBooksByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
  }

  public List<Book> getBooksByAuthor(String author) {
    return bookRepository.findByAuthorContainingIgnoreCase(author);
  }

  public List<Book> getBooksByPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAll(pageable).getContent();
  }

  public Book updateBook(Long id, Book bookDetails) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      Book updatedBook = book.get();
      updatedBook.setTitle(bookDetails.getTitle());
      updatedBook.setAuthor(bookDetails.getAuthor());
      updatedBook.setSummary(bookDetails.getSummary());
      updatedBook.setYear(bookDetails.getYear());
      updatedBook.setGenre(bookDetails.getGenre());
      return bookRepository.save(updatedBook);
    }
    return null;
  }

  public List<Book> searchBooks(String title, String author) {
    if (title != null && !title.isEmpty()) {
      return bookRepository.findByTitleContainingIgnoreCase(title);
    } else if (author != null && !author.isEmpty()) {
      return bookRepository.findByAuthorContainingIgnoreCase(title);
    } else {
      return bookRepository.findAll();
    }
  }
}
