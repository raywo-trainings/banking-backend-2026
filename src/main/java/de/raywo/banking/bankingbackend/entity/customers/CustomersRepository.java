package de.raywo.banking.bankingbackend.entity.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity, UUID> {

  Collection<CustomerEntity> findByNameContainingIgnoreCase(String prefix);

  Collection<CustomerEntity> findByCityContainingIgnoreCase(String city);

  Collection<CustomerEntity> findByNameContainingIgnoreCaseAndCityContainingIgnoreCase(String name, String city);

}
