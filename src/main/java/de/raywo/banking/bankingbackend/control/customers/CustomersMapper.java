package de.raywo.banking.bankingbackend.control.customers;

import de.raywo.banking.bankingbackend.entity.customers.CustomerEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomersMapper {

  @NonNull
  public Customer map(@NonNull CustomerEntity entity) {
    return new Customer(
        entity.getId(),
        entity.getName(),
        entity.getCity());
  }


  @NonNull
  public CustomerEntity map(UUID id, @NonNull Customer customer) {
    return new CustomerEntity(
        id,
        customer.getName(),
        customer.getCity()
    );
  }

}
