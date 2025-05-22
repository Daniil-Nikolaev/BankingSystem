package by.aston.analyticsservice.dto;

import by.aston.analyticsservice.entity.Type;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

public record TransactionLogDto(

        UUID accountId,

        UUID userId,
        @NotNull
        BigDecimal amount,
        @NotNull
        Type type) {
}
