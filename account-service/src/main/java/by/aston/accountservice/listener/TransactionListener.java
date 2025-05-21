package by.aston.accountservice.listener;

import by.aston.accountservice.entity.Account;
import by.aston.accountservice.event.TransactionEvent;
import by.aston.accountservice.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TransactionListener {

    private final AccountRepository accountRepository;

    public TransactionListener(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @KafkaListener(topics = "transactions", groupId = "account-service-group")
    @Transactional
    public void handleTransactionEvent(TransactionEvent event) {

        Optional<Account> senderAccountOpt = accountRepository.findById(event.accountSenderId());
        Optional<Account> receiverAccountOpt = accountRepository.findById(event.accountReceiverId());

        if (senderAccountOpt.isEmpty() || receiverAccountOpt.isEmpty()) {
            throw new InvalidRequestException("Invalid request");
        }

        Account senderAccount = senderAccountOpt.get();
        Account receiverAccount = receiverAccountOpt.get();

        BigDecimal amount = event.amount();
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

    }
}