package by.aston.transactionservice.mapper;

import by.aston.transactionservice.dto.TransactionDto;
import by.aston.transactionservice.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto convertToDto(Transaction transaction);

    Transaction convertToEntity(TransactionDto transactionDto);
}
