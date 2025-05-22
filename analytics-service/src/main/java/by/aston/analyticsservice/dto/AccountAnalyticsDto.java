package by.aston.analyticsservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountAnalyticsDto(
        UUID accountId,
        BigDecimal totalIncome,
        BigDecimal totalOutcome,
        int transcationCount,
        String period
) {
}
