package com.techpro.project.bookcatalog.controller;

import com.techpro.project.bookcatalog.model.BookBasicInfo;
import com.techpro.project.bookcatalog.model.BookDetails;
import com.techpro.project.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
  @Autowired
  private BookService bookService;

  public ResponseEntity<List<BookBasicInfo>> getAllBooks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<BookBasicInfo> books = bookService.getAllBooksBasicInfo(page, size);
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDetails> getBookById(@PathVariable Long id) {
    return bookService.getBookDetailsById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
