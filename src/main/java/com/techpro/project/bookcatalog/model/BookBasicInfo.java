package com.techpro.project.bookcatalog.model;

public class BookBasicInfo {
  private String title;
  private String author;

  public BookBasicInfo(String title, String author) {
    this.title = title;
    this.author = author;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  // Setters (if needed)
}
