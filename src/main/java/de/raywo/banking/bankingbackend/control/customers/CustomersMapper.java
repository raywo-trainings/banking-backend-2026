package de.raywo.banking.bankingbackend.control.customers;

import de.raywo.banking.bankingbackend.entity.customers.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomersMapper {

  public Customer map(CustomerEntity entity) {
    return new Customer(
        entity.getId(),
        entity.getName(),
        entity.getCity());
  }


  public CustomerEntity map(Customer customer) {
    return new CustomerEntity(
        customer.getId(),
        customer.getName(),
        customer.getCity()
    );
  }

}
