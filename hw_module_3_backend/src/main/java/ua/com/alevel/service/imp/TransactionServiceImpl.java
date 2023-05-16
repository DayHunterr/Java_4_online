package ua.com.alevel.service.imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.CategoryType;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.exeption.CustomException;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactionRepository;
import ua.com.alevel.service.TransactionService;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;


    @Override
    public void create(TransactionCreatedDTO entity) {
        validateTransaction(entity);
        Integer senderBalance = accountRepository.findById(entity.getSenderAccId()).get().getBalance();
        Integer receiverBalance = accountRepository.findById(entity.getReceiverAccId()).get().getBalance();
        Account sender = accountRepository.findById(entity.getSenderAccId()).orElseThrow(() ->  new EntityNotFoundException("Entity doesn't exist"));
        Account receiver = accountRepository.findById(entity.getReceiverAccId()).orElseThrow(() ->  new EntityNotFoundException("Entity doesn't exist"));
        Transaction transactionSender = new Transaction();
        transactionSender.setAccount(sender);
        transactionSender.setDescription(entity.getDescription());
        transactionSender.setCategory(CategoryType.EXPENSE);
        transactionSender.setAmount(entity.getAmount());
        Transaction transactionReceiver = new Transaction();
        transactionReceiver.setAccount(sender);
        transactionReceiver.setCategory(CategoryType.INCOME);
        transactionReceiver.setAmount(entity.getAmount());
        transactionReceiver.setDescription("Replenishment from" + sender.getName());
        sender.setBalance(senderBalance - entity.getAmount());
        receiver.setBalance(receiverBalance + entity.getAmount());
        accountRepository.save(sender);
        accountRepository.save(receiver);
        transactionRepository.save(transactionSender);
        transactionRepository.save(transactionReceiver);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exist"));
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Collection<Transaction> findAllByAccountId(Long id) {
        return transactionRepository.findAllByAccountId(id);
    }

    private void validateTransaction(TransactionCreatedDTO transactionDTO) {
        if (transactionDTO.getSenderAccId() == null || transactionDTO.getReceiverAccId() == null) {
            throw new EntityNotFoundException("Id is null");
        }
        if (accountRepository.findById(transactionDTO.getSenderAccId()).isEmpty()) {
            throw new EntityNotFoundException("Sender does not exist");
        }
        if (accountRepository.findById(transactionDTO.getReceiverAccId()).isEmpty()) {
            throw new EntityNotFoundException("Receiver does not exist");
        }
        if (Objects.equals(transactionDTO.getReceiverAccId(), transactionDTO.getSenderAccId())) {
            throw new CustomException("Invalid data");
        }
        if (transactionDTO.getAmount() <= 0) {
            throw new CustomException("Invalid sum");
        }
        if (accountRepository.findById(transactionDTO.getSenderAccId()).get().getBalance() < transactionDTO.getAmount()) {
            throw new CustomException("Insufficient sum");
        }
    }
}
