package de.raywo.banking.bankingbackend.control.shared;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
