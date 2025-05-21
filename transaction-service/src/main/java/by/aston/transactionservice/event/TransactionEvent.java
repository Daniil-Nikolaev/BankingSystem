package by.aston.transactionservice.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionEvent(
        UUID transactionId,
        UUID accountSenderId,
        UUID accountReceiverId,
        LocalDateTime date,
        String currency,
        BigDecimal amount,
        String status
) {}
