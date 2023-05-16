package ua.com.alevel.service;

import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.entity.Transaction;

import java.util.Collection;

public interface TransactionService extends BaseEntityService<Transaction> {

    void create(TransactionCreatedDTO transaction);
    Collection<Transaction> findAllByAccountId(Long id);

}
