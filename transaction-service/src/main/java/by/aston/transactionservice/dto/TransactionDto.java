package by.aston.transactionservice.dto;

import by.aston.transactionservice.entity.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto(
        UUID accountSenderId,

        UUID accountReceiverId,

        @NotNull
        Currency currency,

        @NotNull
        @Positive
        BigDecimal amount,

        String description
) {}

