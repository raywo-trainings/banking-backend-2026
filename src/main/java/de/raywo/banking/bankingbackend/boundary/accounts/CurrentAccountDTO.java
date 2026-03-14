package de.raywo.banking.bankingbackend.boundary.accounts;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentAccountDTO extends AccountDTO {

  private Double overdraftLimit;
  private Float overdraftInterestRate;


  CurrentAccountDTO(
      String iban,
      double balance,
      Double overdraftLimit,
      Float overdraftInterestRate) {
    super(iban, balance, "current");

    this.overdraftLimit = overdraftLimit;
    this.overdraftInterestRate = overdraftInterestRate;
  }


  public static CurrentAccountDTO newFrom(CurrentAccountDTO account) {
    return new CurrentAccountDTO(
        account.getIban(),
        0.0,
        account.getOverdraftLimit(),
        account.getOverdraftInterestRate()
    );
  }


  public static CurrentAccountDTO copyFrom(CurrentAccountDTO account) {
    return new CurrentAccountDTO(
        account.getIban(),
        account.getBalance(),
        account.getOverdraftLimit(),
        account.getOverdraftInterestRate()
    );
  }

}
