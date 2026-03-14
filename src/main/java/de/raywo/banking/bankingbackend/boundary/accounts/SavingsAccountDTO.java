package de.raywo.banking.bankingbackend.boundary.accounts;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SavingsAccountDTO extends AccountDTO {

  private Float interestRate;


  SavingsAccountDTO(
      String iban,
      double balance,
      Float interestRate) {
    super(iban, balance, "savings");

    this.interestRate = interestRate;
  }


  public static SavingsAccountDTO newFrom(SavingsAccountDTO account) {
    return new SavingsAccountDTO(
        account.getIban(),
        0.0,
        account.getInterestRate()
    );
  }


  public static SavingsAccountDTO copyFrom(SavingsAccountDTO account) {
    return new SavingsAccountDTO(
        account.getIban(),
        account.getBalance(),
        account.getInterestRate()
    );
  }

}
