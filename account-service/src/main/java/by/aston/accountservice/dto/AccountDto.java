package by.aston.accountservice.dto;

import by.aston.accountservice.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        @NotNull
        @NotBlank
        UUID userId,
        @NotNull
        @NotBlank
        BigDecimal balance,
        @NotNull
        @NotBlank
        Currency currency
) {}
