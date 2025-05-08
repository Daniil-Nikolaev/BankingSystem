package by.aston.analyticsservice.mapper;

import by.aston.analyticsservice.dto.TransactionLogDto;
import by.aston.analyticsservice.entity.TransactionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionLogMapper {

    TransactionLogDto convertToDto(TransactionLog transactionLog);

    TransactionLog convertToEntity(TransactionLogDto transactionLogDto);


}
