package de.raywo.banking.bankingbackend.entity.customers;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {

  private CustomerSpecs() {}

  public static Specification<CustomerEntity> nameContains(String name) {
    return (root, query, cb) ->
        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<CustomerEntity> cityContains(String city) {
    return (root, query, cb) ->
        cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
  }
}
