package by.aston.analyticsservice;

import by.aston.analyticsservice.dto.AccountAnalyticsDto;
import by.aston.analyticsservice.dto.UserAnalyticsDto;
import by.aston.analyticsservice.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    // 1. По всем счетам пользователя (за всё время)
    @Query("SELECT new by.aston.analyticsservice.dto.UserAnalyticsDto(t.accountId, " +
            "SUM(CASE WHEN t.type = 'INCOMING' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'OUTGOING' THEN t.amount ELSE 0 END), " +
            "COUNT(t), NULL) " +
            "FROM TransactionLog t WHERE t.accountId IN :accountIds GROUP BY t.accountId")
    List<UserAnalyticsDto> getUserAnalytics(List<UUID> accountIds);

    // 2. По всем счетам пользователя за месяц
    @Query("SELECT new by.aston.analyticsservice.dto.UserAnalyticsDto(t.accountId, " +
            "SUM(CASE WHEN t.type = 'INCOMING' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'OUTGOING' THEN t.amount ELSE 0 END), " +
            "COUNT(t), :month) " +
            "FROM TransactionLog t WHERE t.accountId IN :accountIds AND FUNCTION('TO_CHAR', t.createdAt, 'YYYY-MM') = :month GROUP BY t.accountId")
    List<UserAnalyticsDto> getUserAnalyticsByMonth(List<UUID> accountIds, String month);

    // 3. По конкретному счёту за всё время
    @Query("SELECT new by.aston.analyticsservice.dto.AccountAnalyticsDto(t.accountId, " +
            "SUM(CASE WHEN t.type = 'INCOMING' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'OUTGOING' THEN t.amount ELSE 0 END), " +
            "COUNT(t), NULL) " +
            "FROM TransactionLog t WHERE t.accountId = :accountId GROUP BY t.accountId")
    AccountAnalyticsDto getAccountAnalytics(UUID accountId);

    // 4. По конкретному счёту за месяц
    @Query("SELECT new by.aston.analyticsservice.dto.AccountAnalyticsDto(t.accountId, " +
            "SUM(CASE WHEN t.type = 'INCOMING' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'OUTGOING' THEN t.amount ELSE 0 END), " +
            "COUNT(t), :month) " +
            "FROM TransactionLog t WHERE t.accountId = :accountId AND FUNCTION('TO_CHAR', t.createdAt, 'YYYY-MM') = :month GROUP BY t.accountId")
    AccountAnalyticsDto getAccountAnalyticsByMonth(UUID accountId, String month);
}
