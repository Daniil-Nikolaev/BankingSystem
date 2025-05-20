package by.aston.accountservice.mapper;

import by.aston.accountservice.dto.AccountDto;
import by.aston.accountservice.entity.Account;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper {

//    AccountDto convertToDto(Account account);

//    Account convertToEntity(AccountDto accountDto);

    default Account convertToEntity(AccountDto accountDto){
        Account account = new Account();
        account.setBalance(accountDto.balance());
        account.setUserId(accountDto.userId());
        account.setCurrency(accountDto.currency());
        return account;
    }

    default AccountDto convertToDto(Account account){
        AccountDto accountDto=new AccountDto(account.getUserId(),account.getBalance(),account.getCurrency());
        return accountDto;
    }
}
