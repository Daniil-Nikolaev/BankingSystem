package by.aston.analyticsservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "transactions_log")
public class TransactionLog {
    @Id
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(nullable = false)
    private UUID accountId;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

}
