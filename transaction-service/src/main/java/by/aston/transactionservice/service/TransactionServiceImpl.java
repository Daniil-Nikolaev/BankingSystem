package by.aston.transactionservice.service;

import by.aston.transactionservice.dto.TransactionDto;
import by.aston.transactionservice.entity.Status;
import by.aston.transactionservice.entity.Transaction;
import by.aston.transactionservice.event.TransactionEvent;
import by.aston.transactionservice.mapper.TransactionMapper;
import by.aston.transactionservice.repository.TransactionRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repo;
    private final TransactionMapper mapper;
    private final KafkaTemplate<String, TransactionEvent> kafka;

    public TransactionServiceImpl(TransactionRepository repo,
                                  TransactionMapper mapper,
                                  KafkaTemplate<String, TransactionEvent> kafka) {
        this.repo    = repo;
        this.mapper  = mapper;
        this.kafka   = kafka;
    }

    @Override
    @Transactional
    public TransactionDto refill(String accountId, TransactionDto dto) {
        var tx = mapper.toEntity(dto);
        tx.setAccountReceiverId(UUID.fromString(accountId));
        tx.setAccountSenderId(null);
        tx.setDate(LocalDateTime.now());
        tx.setStatus(Status.COMPLETED);
        repo.save(tx);
        publish(tx);
        return mapper.toDto(tx);
    }

    @Override
    @Transactional
    public TransactionDto withdrawal(String accountId, TransactionDto dto) {
        var tx = mapper.toEntity(dto);
        tx.setAccountSenderId(UUID.fromString(accountId));
        tx.setAccountReceiverId(null);
        tx.setDate(LocalDateTime.now());
        tx.setStatus(Status.COMPLETED);
        repo.save(tx);
        publish(tx);
        return mapper.toDto(tx);
    }

    @Override
    @Transactional
    public TransactionDto transfer(TransactionDto dto) {
        var tx = mapper.toEntity(dto);
        tx.setDate(LocalDateTime.now());
        tx.setStatus(Status.PENDING);
        repo.save(tx);
        publish(tx);
        return mapper.toDto(tx);
    }

    private void publish(Transaction tx) {
        var event = new TransactionEvent(
                tx.getId(),
                tx.getAccountSenderId(),
                tx.getAccountReceiverId(),
                tx.getDate(),
                tx.getCurrency().name(),
                tx.getAmount(),
                tx.getStatus().name()
        );
        kafka.send("transactions", event);
    }
}
