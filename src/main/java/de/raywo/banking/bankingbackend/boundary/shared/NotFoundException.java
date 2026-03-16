package de.raywo.banking.bankingbackend.boundary.shared;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
