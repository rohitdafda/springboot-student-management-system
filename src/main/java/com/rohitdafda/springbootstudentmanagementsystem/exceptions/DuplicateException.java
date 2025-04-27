package com.rohitdafda.springbootstudentmanagementsystem.exceptions;

public class DuplicateException extends RuntimeException {

  public DuplicateException(String message) {
    super(message);
  }
}
