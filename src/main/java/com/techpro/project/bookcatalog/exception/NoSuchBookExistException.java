package com.techpro.project.bookcatalog.exception;

public class NoSuchBookExistException extends RuntimeException {
  private String message;

  public NoSuchBookExistException() {
  }

  public NoSuchBookExistException(String msg) {
    super(msg);
    this.message = msg;
  }
}
