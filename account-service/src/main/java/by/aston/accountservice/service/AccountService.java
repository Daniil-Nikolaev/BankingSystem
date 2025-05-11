package by.aston.accountservice.service;

import by.aston.accountservice.dto.AccountDto;
import by.aston.accountservice.entity.Account;
import by.aston.accountservice.mapper.AccountMapper;
import by.aston.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public void createAccount(AccountDto accountDto) {
        Account account=accountMapper.convertToEntity(accountDto);
        accountRepository.save(account);
    }

    public AccountDto getAccountById(UUID id) {
      Optional<Account> account=accountRepository.findAccountById(id);
      if(account.isEmpty()){
          throw new IllegalArgumentException("Account not found");
      }
        return accountMapper.convertToDto(account.get());
    }

    public List<AccountDto> getAccounts(UUID id) {
        List<Account> accounts=accountRepository.findAccountsByUserId(id);
        if(accounts.isEmpty()){
            throw new IllegalArgumentException("Accounts not found");
        }
        return accounts.stream().
                map(accountMapper::convertToDto).
                collect(Collectors.toList());
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
    //Реализовать изменение баланса по событию из Kafka
}
