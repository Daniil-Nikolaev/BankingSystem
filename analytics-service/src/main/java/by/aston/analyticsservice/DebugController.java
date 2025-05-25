package by.aston.analyticsservice;


import by.aston.analyticsservice.entity.TransactionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class DebugController {

    private final TransactionLogRepository repository;

    @GetMapping("/all")
    public List<TransactionLog> getAll() {
        return repository.findAll();
    }
}
