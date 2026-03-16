package de.raywo.banking.bankingbackend.control.customers;

import de.raywo.banking.bankingbackend.control.shared.OnCreate;
import de.raywo.banking.bankingbackend.control.shared.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private UUID id;

  @NotNull
  @Size(min = 2, max = 100)
  private final String name;

  @NotNull
  @Size(min = 2, max = 100)
  private final String city;

}
