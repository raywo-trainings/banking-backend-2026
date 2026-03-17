package de.raywo.banking.bankingbackend.control.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomersDataInitializer {

  private final CustomersService customersService;


  @EventListener(ApplicationReadyEvent.class)
  public void initialize() {
    Customer cust1 = new Customer("Max Musterfrau", "Hamburg");
    customersService.createCustomer(cust1);
    Customer cust2 = new Customer("Peter Mustermann", "Berlin");
    customersService.createCustomer(cust2);
  }

}
