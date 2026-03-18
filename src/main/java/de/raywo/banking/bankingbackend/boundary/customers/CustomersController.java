package de.raywo.banking.bankingbackend.boundary.customers;

import de.raywo.banking.bankingbackend.control.customers.Customer;
import de.raywo.banking.bankingbackend.control.customers.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomersController {

  private final CustomersService customersService;
  private final CustomersDTOMapper mapper;


  @GetMapping()
  public Collection<CustomerDTO> getCustomers(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String city
  ) {
    return customersService.getCustomers(name, city)
        .stream()
        .map(mapper::map)
        .toList();
  }


  @GetMapping("/{id}")
  public CustomerDTO getCustomer(@PathVariable String id) {
    var uuid = UUID.fromString(id);

    return mapper.map(customersService.getCustomer(uuid));
  }


  @PostMapping
  public ResponseEntity<CustomerDTO> createCustomer(
      @RequestBody @Valid CustomerDTO customer,
      UriComponentsBuilder uriBuilder
  ) {
    Customer newCustomer = customersService
        .createCustomer(mapper.map(null, customer));
    String id = newCustomer.getId().toString();

    URI location = uriBuilder
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();

    return ResponseEntity
        .created(location)
        .body(mapper.map(newCustomer));
  }


  @PutMapping("/{id}")
  public CustomerDTO updateCustomer(
      @PathVariable String id,
      @RequestBody @Valid CustomerDTO customer
  ) {
    Customer updatedCustomer = customersService.updateCustomer(
        UUID.fromString(id),
        mapper.map(id, customer)
    );

    return mapper.map(updatedCustomer);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable String id) {
    customersService.deleteCustomer(UUID.fromString(id));
  }

}
