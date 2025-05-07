package by.aston.transactionservice.dto;

import by.aston.transactionservice.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import java.util.UUID;

public record TransactionDto(
        @NotNull
        @NotBlank
        UUID accountSenderId,
        @NotNull
        @NotBlank
        UUID accountReceiverId,
        @NotNull
        @NotBlank
        Currency currency,
        @NotNull
        @NotBlank
        BigDecimal amount,
        String description) {
}
