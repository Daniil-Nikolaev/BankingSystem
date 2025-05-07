package by.aston.analyticsservice.dto;

import by.aston.analyticsservice.entity.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

public record TransactionLogDto(
        @NotNull
        @NotBlank
        UUID accountId,
        @NotNull
        @NotBlank
        BigDecimal amount,
        @NotNull
        @NotBlank
        Type type) {
}
