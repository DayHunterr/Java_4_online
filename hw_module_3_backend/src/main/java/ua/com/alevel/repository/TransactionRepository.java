package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.CategoryType;
import ua.com.alevel.entity.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends BaserEntityRepository<Transaction> {
    List<Transaction> findAllByAccountId(Long accountId);
    Optional<Transaction> findByAccountIdAndCategory(Long accountId, CategoryType type);
}
