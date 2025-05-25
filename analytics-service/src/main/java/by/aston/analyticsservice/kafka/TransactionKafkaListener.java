package by.aston.analyticsservice.kafka;

import by.aston.analyticsservice.TransactionLogRepository;
import by.aston.analyticsservice.dto.TransactionLogDto;
import by.aston.analyticsservice.entity.TransactionLog;
import by.aston.analyticsservice.mapper.TransactionLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionKafkaListener {

    private  final TransactionLogRepository transactionLogRepository;
    private final TransactionLogMapper transactionLogMapper;

    @KafkaListener(topics = "transaction-events", groupId = "analytics",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(TransactionLogDto dto) {
        TransactionLog log = transactionLogMapper.convertToEntity(dto);
        log.setAccountId(dto.accountId());
        log.setUserId(dto.userId());
        log.setAmount(dto.amount());
        log.setType(dto.type());
        transactionLogRepository.save(log);

        System.out.println("Saved in base" + log);
    }

}
