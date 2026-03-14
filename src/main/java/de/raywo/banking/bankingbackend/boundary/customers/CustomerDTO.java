package de.raywo.banking.bankingbackend.boundary.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  private String name;
  private String city;

}
