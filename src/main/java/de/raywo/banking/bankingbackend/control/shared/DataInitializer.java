package de.raywo.banking.bankingbackend.control.shared;

import de.raywo.banking.bankingbackend.control.accounts.Account;
import de.raywo.banking.bankingbackend.control.accounts.AccountsService;
import de.raywo.banking.bankingbackend.control.accounts.CurrentAccount;
import de.raywo.banking.bankingbackend.control.accounts.SavingsAccount;
import de.raywo.banking.bankingbackend.control.customers.Customer;
import de.raywo.banking.bankingbackend.control.customers.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

  private final AccountsService accountsService;
  private final CustomersService customersService;


  @EventListener(ApplicationReadyEvent.class)
  public void initialize() {
    Customer customer1 = new Customer("Max Musterfrau", "Hamburg");
    Customer customer2 = new Customer("Peter Mustermann", "Berlin");

    customersService.createCustomer(customer1);
    customersService.createCustomer(customer2);

    CurrentAccount account1 = new CurrentAccount("DE12180500004405123456", customer1);
    Account account2 = new SavingsAccount("DE12180500005405234567", customer2);

    accountsService.createCurrentAccount(account1);
    accountsService.createAccount(account2);
  }

}
