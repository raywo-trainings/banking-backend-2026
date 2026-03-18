package de.raywo.banking.bankingbackend.control.shared;

public class UnknownCustomerException extends RuntimeException {
  public UnknownCustomerException(String message) {
    super(message);
  }
}
