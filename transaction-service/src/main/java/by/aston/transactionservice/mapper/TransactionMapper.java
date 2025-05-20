package by.aston.transactionservice.mapper;

import by.aston.transactionservice.dto.TransactionDto;
import by.aston.transactionservice.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "accountSenderId",   source = "accountSenderId")
    @Mapping(target = "accountReceiverId", source = "accountReceiverId")
    @Mapping(target = "currency",          source = "currency")    // ← обязательно
    @Mapping(target = "amount",            source = "amount")
    @Mapping(target = "description",       source = "description")
    Transaction toEntity(TransactionDto dto);

    @Mapping(target = "accountSenderId",   source = "accountSenderId")
    @Mapping(target = "accountReceiverId", source = "accountReceiverId")
    @Mapping(target = "currency",          source = "currency")
    @Mapping(target = "amount",            source = "amount")
    @Mapping(target = "description",       source = "description")
    TransactionDto toDto(Transaction entity);
}
