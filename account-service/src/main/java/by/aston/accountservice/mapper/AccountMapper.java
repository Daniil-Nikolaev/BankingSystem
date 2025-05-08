package by.aston.accountservice.mapper;

import by.aston.accountservice.dto.AccountDto;
import by.aston.accountservice.entity.Account;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto convertToDto(Account account);

    Account convertToEntity(AccountDto accountDto);
}
