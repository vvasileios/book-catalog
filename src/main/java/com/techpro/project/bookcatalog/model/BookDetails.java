package com.techpro.project.bookcatalog.model;

public class BookDetails {
  private String title;
  private String author;
  private String summary;
  private int year;
  private String genre;

  public BookDetails(String title, String author, String summary, int year, String genre) {
    this.title = title;
    this.author = author;
    this.summary = summary;
    this.year = year;
    this.genre = genre;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getSummary() {
    return summary;
  }

  public int getYear() {
    return year;
  }

  public String getGenre() {
    return genre;
  }

  // Setters (if needed)
}
