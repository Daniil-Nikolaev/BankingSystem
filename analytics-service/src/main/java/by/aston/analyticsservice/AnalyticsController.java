package by.aston.analyticsservice;

import by.aston.analyticsservice.dto.AccountAnalyticsDto;
import by.aston.analyticsservice.dto.UserAnalyticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/analytics")
public class AnalyticsController {
    private final TransactionLogRepository transactionLogRepository;
    private final AnalyticsService analyticsService;

    // Аналитика по одному счету за всё время
    @GetMapping("/account/{accountId}")
    public ResponseEntity<AccountAnalyticsDto> getAccountAnalytics(@PathVariable UUID accountId) {
        return ResponseEntity.ok(analyticsService.getAccountAnalytics(accountId));
    }

    // Аналитика по одному счету за конкретный месяц
    @GetMapping("/account/{accountId}/month")
    public ResponseEntity<AccountAnalyticsDto> getAccountAnalyticsByMonth(
            @PathVariable UUID accountId,
            @RequestParam String month // формат "YYYY-MM"
    ) {
        return ResponseEntity.ok(analyticsService.getAccountAnalyticsByMonth(accountId, month));
    }

    // Аналитика по всем счетам пользователя за всё время
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAnalyticsDto>> getUserAnalytics(@PathVariable UUID userId) {
        return ResponseEntity.ok(analyticsService.getUserAnalytics(userId));
    }

    // По всем счетам пользователя за месяц:
    @GetMapping("/user/{userId}/month")
    public ResponseEntity<List<UserAnalyticsDto>> getUserAnalyticsByMonth(
            @PathVariable UUID userId,
            @RequestParam String month
    ) {
        return ResponseEntity.ok(analyticsService.getUserAnalyticsByMonth(userId, month));
    }
}
