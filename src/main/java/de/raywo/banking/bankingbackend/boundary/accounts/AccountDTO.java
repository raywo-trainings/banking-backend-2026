package de.raywo.banking.bankingbackend.boundary.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String iban;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private double balance;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String accountType;


  public AccountDTO(String iban, String accountType) {
    this.iban = iban;
    this.accountType = accountType;
    this.balance = 0;
  }

}
