package de.raywo.banking.bankingbackend.control.customers;


import de.raywo.banking.bankingbackend.control.shared.NotFoundException;
import de.raywo.banking.bankingbackend.control.shared.OnWrite;
import de.raywo.banking.bankingbackend.entity.customers.CustomerEntity;
import de.raywo.banking.bankingbackend.entity.customers.CustomerSpecs;
import de.raywo.banking.bankingbackend.entity.customers.CustomersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersRepository repo;
  private final CustomersMapper mapper;


  public Collection<Customer> getCustomers(String name, String city) {
    Specification<CustomerEntity> spec = Specification.where(
        (Specification<CustomerEntity>) null
    );

    if (name != null) {
      spec = spec.and(CustomerSpecs.nameContains(name));
    }

    if (city != null) {
      spec = spec.and(CustomerSpecs.cityContains(city));
    }

    return repo.findAll(spec)
        .stream()
        .map(mapper::map)
        .toList();
  }


  public Customer getCustomer(UUID id) {
    return repo.findById(id)
        .map(mapper::map)
        .orElseThrow(() -> new NotFoundException("Customer with id " + id + " does not exist"));
  }


  @Validated(OnWrite.class)
  public Customer createCustomer(@Valid Customer customer) {
    return mapper.map(repo.save(mapper.map(null, customer)));
  }


  @Validated(OnWrite.class)
  public Customer updateCustomer(UUID id, @Valid Customer customer) {
    requireCustomerExists(id);

    return mapper.map(repo.save(mapper.map(id, customer)));
  }


  public void deleteCustomer(UUID id) {
    requireCustomerExists(id);
    repo.deleteById(id);
  }


  public long getCustomerCount() {
    return repo.count();
  }



  private void requireCustomerExists(UUID id) {
    if (!repo.existsById(id)) {
      throw new NotFoundException("Customer with id " + id + " does not exist");
    }
  }

}
