package de.raywo.banking.bankingbackend.boundary.accounts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/current-accounts")
public class CurrentAccountsController {

  private final Map<String, CurrentAccountDTO> currentAccounts = new HashMap<>();


  public CurrentAccountsController() {
    String iban1 = "DE12145678";
    CurrentAccountDTO account1 = new CurrentAccountDTO(iban1, 100.0, 0.0, 0.11f);
    String iban2 = "DE17165546";
    CurrentAccountDTO account2 = new CurrentAccountDTO(iban2, 200.0, 100.0, 0.12f);

    currentAccounts.put(iban1, account1);
    currentAccounts.put(iban2, account2);
  }


  @GetMapping
  public Collection<CurrentAccountDTO> getCurrentAccounts() {
    return currentAccounts.values();
  }


  @PostMapping
  public ResponseEntity<CurrentAccountDTO> createAccount(
      @RequestBody CurrentAccountDTO account,
      UriComponentsBuilder uriBuilder
  ) {
    if (currentAccounts.containsKey(account.getIban())) {
      return ResponseEntity.badRequest().build();
    }

    if (!account.getAccountType().equals("current")) {
      return ResponseEntity.badRequest().build();
    }

    var iban = account.getIban();
    var newAccount = CurrentAccountDTO.newFrom(account);

    currentAccounts.put(iban, newAccount);
    var location = uriBuilder
        .path("/{iban}")
        .buildAndExpand(iban)
        .toUri();

    return ResponseEntity
        .created(location)
        .body(newAccount);
  }


  @GetMapping("/{iban}")
  public ResponseEntity<CurrentAccountDTO> getCurrentAccount(
      @PathVariable String iban
  ) {
    if (!currentAccounts.containsKey(iban)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(currentAccounts.get(iban));
  }


  @PutMapping("/{iban}")
  public ResponseEntity<CurrentAccountDTO> updateAccount(
      @PathVariable String iban,
      @RequestBody CurrentAccountDTO account
  ) {
    if (!currentAccounts.containsKey(iban)) {
      return ResponseEntity.notFound().build();
    }

    var copiedAccount = CurrentAccountDTO.copyFrom(account);

    currentAccounts.put(iban, copiedAccount);

    return ResponseEntity.ok(copiedAccount);
  }


  @DeleteMapping("/{iban}")
  public ResponseEntity<Void> deleteAccount(
      @PathVariable String iban
  ) {
    if (!currentAccounts.containsKey(iban)) {
      return ResponseEntity.notFound().build();
    }

    currentAccounts.remove(iban);

    return ResponseEntity.noContent().build();
  }
}
