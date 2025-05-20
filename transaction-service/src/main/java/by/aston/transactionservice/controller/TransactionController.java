package by.aston.transactionservice.controller;

import by.aston.transactionservice.dto.TransactionDto;
import by.aston.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService svc;

    public TransactionController(TransactionService svc) {
        this.svc = svc;
    }

    @PostMapping("/refill/{accountId}")
    public ResponseEntity<TransactionDto> refill(
            @PathVariable String accountId,
            @Valid @RequestBody TransactionDto dto
    ) {
        return ResponseEntity.ok(svc.refill(accountId, dto));
    }

    @PostMapping("/withdrawal/{accountId}")
    public ResponseEntity<TransactionDto> withdrawal(
            @PathVariable String accountId,
            @Valid @RequestBody TransactionDto dto
    ) {
        return ResponseEntity.ok(svc.withdrawal(accountId, dto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transfer(
            @Valid @RequestBody TransactionDto dto
    ) {
        return ResponseEntity.ok(svc.transfer(dto));
    }
}
