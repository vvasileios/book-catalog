package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.BookBasicInfo;
import com.techpro.project.bookcatalog.model.BookDetails;
import com.techpro.project.bookcatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  // List books endpoint
  public List<BookBasicInfo> getAllBooksBasicInfo(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAll(pageable).stream()
        .map(book -> new BookBasicInfo(book.getTitle(), book.getAuthor()))
        .collect(Collectors.toList());
  }

  // Book details endpoint
  public Optional<BookDetails> getBookDetailsById(Long id) {
    // Find the book by ID and map it to BookDetails DTO if present
    return bookRepository.findById(id)
        .map(book -> new BookDetails(
            book.getTitle(),
            book.getAuthor(),
            book.getSummary(),
            book.getYear(),
            book.getGenre()));
  }
}
