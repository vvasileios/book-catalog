package com.techpro.project.bookcatalog.repository;

import com.techpro.project.bookcatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  // List<Book> findByTitleContainingIgnoreCase(String title);

  // List<Book> findByAuthorContainingIgnoreCase(String author);
}
