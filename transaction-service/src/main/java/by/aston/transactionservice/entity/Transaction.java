package by.aston.transactionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(nullable = false)
    private UUID accountSenderId;
    @Column(nullable = false)
    private UUID accountReceiverId;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
}
