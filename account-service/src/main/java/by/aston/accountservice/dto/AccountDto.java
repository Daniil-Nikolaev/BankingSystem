package by.aston.accountservice.dto;

import by.aston.accountservice.entity.Currency;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        @NotNull
        UUID userId,
        @NotNull
        BigDecimal balance,
        @NotNull
        Currency currency
) {}
