package by.aston.transactionservice.service;

import by.aston.transactionservice.dto.TransactionDto;

public interface TransactionService {
    TransactionDto refill      (String accountId, TransactionDto dto);
    TransactionDto withdrawal  (String accountId, TransactionDto dto);
    TransactionDto transfer    (TransactionDto dto);
}
