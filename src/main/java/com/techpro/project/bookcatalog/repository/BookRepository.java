package com.techpro.project.bookcatalog.repository;

import com.techpro.project.bookcatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByPublished(boolean published);

  List<Book> findByTitleContainingIgnoreCase(String title);

  List<Book> findByAuthorContainingIgnoreCase(String author);
}
