package de.raywo.banking.bankingbackend.control.shared;

import de.raywo.banking.bankingbackend.control.customers.Customer;
import de.raywo.banking.bankingbackend.control.customers.CustomersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

  private final CustomersService customersService;


  @EventListener(ApplicationReadyEvent.class)
  public void initialize() {
    log.debug("Checking for existing customers...");
    if (customersService.getCustomerCount() > 0) {
      log.debug("Customers already exist, skipping initialization");
      return;
    }

    log.info("Initializing customers...");
    Customer customer1 = new Customer("Max Musterfrau", "Hamburg");
    Customer customer2 = new Customer("Peter Mustermann", "Berlin");

    customersService.createCustomer(customer1);
    customersService.createCustomer(customer2);
    log.info("Customers initialized");
  }

}
