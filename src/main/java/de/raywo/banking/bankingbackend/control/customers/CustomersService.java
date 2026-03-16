package de.raywo.banking.bankingbackend.control.customers;


import de.raywo.banking.bankingbackend.control.shared.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Validated
public class CustomersService {

  private final Map<UUID, Customer> customers = new HashMap<>();


  public Collection<Customer> getCustomers() {
    return customers.values();
  }


  public Customer getCustomer(UUID id) {
    requireCustomerExists(id);

    return customers.get(id);
  }


  public Customer createCustomer(@Valid Customer customer) {
    UUID newId = UUID.randomUUID();
    Customer newCustomer = new Customer(
        newId,
        customer.getName(),
        customer.getCity()
    );

    customers.put(newId, newCustomer);

    return newCustomer;
  }


  public Customer updateCustomer(UUID id, @Valid Customer customer) {
    requireCustomerExists(id);

    Customer updatedCustomer = new Customer(
        id,
        customer.getName(),
        customer.getCity()
    );
    customers.put(id, updatedCustomer);

    return updatedCustomer;
  }


  public void deleteCustomer(UUID id) {
    requireCustomerExists(id);
    customers.remove(id);
  }


  private void requireCustomerExists(UUID id) {
    if (!customers.containsKey(id)) {
      throw new NotFoundException("Customer with id " + id + " does not exist");
    }
  }

}
