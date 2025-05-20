package by.aston.transactionservice.dto;

import by.aston.transactionservice.entity.Currency;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import java.util.UUID;

public record TransactionDto(
        @NotNull
        UUID accountSenderId,
        @NotNull
        UUID accountReceiverId,
        @NotNull
        Currency currency,
        @NotNull
        BigDecimal amount,
        String description) {
}
