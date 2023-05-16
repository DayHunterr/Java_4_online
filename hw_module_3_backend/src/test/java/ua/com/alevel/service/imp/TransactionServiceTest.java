package ua.com.alevel.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.CategoryType;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactionRepository;
import ua.com.alevel.service.TransactionService;


import java.util.Optional;

@SpringBootTest
@Transactional
/*
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
*/
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private Long senderAccountId = 1L;
    private Long receiverAccountId = 2L;
    private Integer amount = 500;
    private String description = "dick";

    @BeforeEach
    public void setup() {
        Account senderAccount = new Account();
        senderAccount.setName("Sender");
        senderAccount.setBalance(1000);
        accountRepository.save(senderAccount);
        senderAccountId = senderAccount.getId();

        Account receiverAccount = new Account();
        receiverAccount.setName("Receiver");
        receiverAccount.setBalance(0);
        accountRepository.save(receiverAccount);
        receiverAccountId = receiverAccount.getId();
    }

    @Test
    public void testCreateTransaction() {
        // Arrange
        TransactionCreatedDTO transactionDTO = new TransactionCreatedDTO(description,amount,senderAccountId,receiverAccountId);
        transactionDTO.setDescription(description);
        transactionDTO.setAmount(amount);
        transactionDTO.setSenderAccId(senderAccountId);
        transactionDTO.setReceiverAccId(receiverAccountId);


        // Act
        transactionService.create(transactionDTO);

        // Assert
        Optional<Account> senderAccount = accountRepository.findById(senderAccountId);
        Optional<Account> receiverAccount = accountRepository.findById(receiverAccountId);
        Optional<Transaction> senderTransaction = transactionRepository.findByAccountIdAndCategory(senderAccountId, CategoryType.EXPENSE);

        Assertions.assertTrue(senderTransaction.isPresent());
        Assertions.assertTrue(senderAccount.isPresent());
        Assertions.assertTrue(receiverAccount.isPresent());

        Assertions.assertEquals(500, senderTransaction.get().getAmount());
        Assertions.assertEquals(500, senderAccount.get().getBalance());
        Assertions.assertEquals(500, receiverAccount.get().getBalance());

    }
}
