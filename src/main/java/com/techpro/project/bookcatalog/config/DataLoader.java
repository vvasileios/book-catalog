package com.techpro.project.bookcatalog.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpro.project.bookcatalog.model.Book;
import com.techpro.project.bookcatalog.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

  private final BookRepository bookRepository;

  public DataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {
    };
    InputStream inputStream = new ClassPathResource("books.json").getInputStream();
    try {
      List<Book> books = mapper.readValue(inputStream, typeReference);
      bookRepository.saveAll(books);
      System.out.println("Books saved to the database.");
    } catch (Exception e) {
      System.out.println("Unable to save books: " + e.getMessage());
    }
  }
}
