package by.aston.analyticsservice;

import by.aston.analyticsservice.dto.AccountAnalyticsDto;
import by.aston.analyticsservice.dto.UserAnalyticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final TransactionLogRepository transactionLogRepository;

    // 1. Аналитика по всем счетам пользователя за всё время
    public List<UserAnalyticsDto> getUserAnalytics(UUID userId) {
        return transactionLogRepository.getUserAnalytics(Collections.singletonList(userId));
    }

    // 2. Аналитика по всем счетам пользователя за месяц
    public List<UserAnalyticsDto> getUserAnalyticsByMonth(UUID userId, String month) {
        return transactionLogRepository.getUserAnalyticsByMonth(Collections.singletonList(userId), month);
    }

    // 3. Аналитика по конкретному счету за всё время
    public AccountAnalyticsDto getAccountAnalytics(UUID accountId) {
        return transactionLogRepository.getAccountAnalytics(accountId);
    }

    // 4. Аналитика по конкретному счету за месяц
    public AccountAnalyticsDto getAccountAnalyticsByMonth(UUID accountId, String month) {
        return transactionLogRepository.getAccountAnalyticsByMonth(accountId, month);
    }
}
