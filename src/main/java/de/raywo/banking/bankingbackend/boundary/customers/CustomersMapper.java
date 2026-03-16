package de.raywo.banking.bankingbackend.boundary.customers;

import de.raywo.banking.bankingbackend.control.customers.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomersMapper {

  public CustomerDTO map(Customer customer) {
    return new CustomerDTO(
        customer.getId().toString(),
        customer.getName(),
        customer.getCity()
    );
  }


  public Customer map(String id, CustomerDTO customerDTO) {
    return new Customer(
        UUID.fromString(id),
        customerDTO.getName(),
        customerDTO.getCity()
    );
  }

}
