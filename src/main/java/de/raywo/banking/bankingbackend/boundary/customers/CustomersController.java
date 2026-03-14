package de.raywo.banking.bankingbackend.boundary.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/customers")
public class CustomersController {

  private final Map<String, CustomerDTO> customers = new HashMap<>();


  public CustomersController() {
    String id1 = generateId();
    customers.put(id1, new CustomerDTO(id1, "John Doe", "New York"));
    String id2 = generateId();
    customers.put(id2, new CustomerDTO(id2, "Jane Smith", "Los Angeles"));
  }


  @GetMapping()
  public Collection<CustomerDTO> getCustomers() {
    return customers.values();
  }


  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String id) {
    if (!customers.containsKey(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(customers.get(id));
  }


  @PostMapping
  public ResponseEntity<CustomerDTO> createCustomer(
      @RequestBody CustomerDTO customer,
      UriComponentsBuilder uriBuilder
  ) {
    final String id = generateId();
    CustomerDTO newCustomer = new CustomerDTO(
        id,
        customer.getName(),
        customer.getCity()
    );
    customers.put(id, newCustomer);

    URI location = uriBuilder
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();

    return ResponseEntity
        .created(location)
        .body(newCustomer);
  }


  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> updateCustomer(
      @PathVariable String id,
      @RequestBody CustomerDTO customer
  ) {
    if (!customers.containsKey(id)) {
      return ResponseEntity.notFound().build();
    }

    CustomerDTO updatedCustomer = new CustomerDTO(
        id,
        customer.getName(),
        customer.getCity()
    );

    customers.put(id, updatedCustomer);

    return ResponseEntity.ok(updatedCustomer);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
    customers.remove(id);

    return ResponseEntity.noContent().build();
  }


  private String generateId() {
    return customers.keySet()
        .stream()
        .map(Integer::parseInt)
        .max(Comparator.naturalOrder())
        .map(n -> n + 1)
        .map(String::valueOf)
        .orElse("0");
  }

}
