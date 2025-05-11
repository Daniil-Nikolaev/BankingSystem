package by.aston.accountservice.repository;

import by.aston.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAccountsByUserId(UUID userId);
    Optional<Account> findAccountById(UUID id);
}
