package by.aston.accountservice.controller;


import by.aston.accountservice.dto.AccountDto;
import by.aston.accountservice.entity.Account;
import by.aston.accountservice.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
       this.accountService = accountService;
   }

   @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountDto accountDto, BindingResult bindingResult) {
       if (bindingResult.hasErrors()) {
           return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
       }
       accountService.createAccount(accountDto);
       return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @GetMapping()
    public ResponseEntity<List<AccountDto>> getAccounts(@RequestHeader("X-User-Id") UUID userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<AccountDto> accounts = accountService.getAccounts(userId);
       return new ResponseEntity<>(accounts,HttpStatus.OK);
   }

   @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID accountId) {
       if(accountId == null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       AccountDto account= accountService.getAccountById(accountId);
        return new ResponseEntity<>(account,HttpStatus.OK);
   }

   @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable UUID accountId) {
       if(accountId == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       accountService.deleteAccount(accountId);
       return new ResponseEntity<>(HttpStatus.OK);
   }
}
