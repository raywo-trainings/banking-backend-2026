package de.raywo.banking.bankingbackend.control.shared;

import de.raywo.banking.bankingbackend.control.customers.Customer;
import de.raywo.banking.bankingbackend.control.customers.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

  private final CustomersService customersService;


  @EventListener(ApplicationReadyEvent.class)
  public void initialize() {
    Customer customer1 = new Customer("Max Musterfrau", "Hamburg");
    Customer customer2 = new Customer("Peter Mustermann", "Berlin");

    customersService.createCustomer(customer1);
    customersService.createCustomer(customer2);
  }

}
