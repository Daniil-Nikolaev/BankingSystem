package by.aston.analyticsservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserAnalyticsDto (
        UUID userId,
        BigDecimal totalIncome,
        BigDecimal totalOutcome,
        int transactionCount,
        String period
){
}
