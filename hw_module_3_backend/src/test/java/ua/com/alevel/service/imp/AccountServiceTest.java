package ua.com.alevel.service.imp;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;
import ua.com.alevel.exeption.CustomException;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactionRepository;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.AccountService;

import java.util.Collection;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private Long userId;
    private Long accountId;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setName("User");
        userRepository.save(user);

        // Create an account associated with the user
        Account account = new Account();
        account.setName("Account");
        account.setBalance(1000);
        account.setUser(user);

        accountRepository.save(account);

        userId = user.getId();
        accountId = account.getId();
    }

    @Test
    public void testUpdateAccount() {
        // Arrange

        Account accountToUpdate = accountRepository.findById(accountId).orElseThrow();

        accountToUpdate.setName("UpdatedAccount");
        accountToUpdate.setBalance(2000);

        Assertions.assertEquals("UpdatedAccount", accountToUpdate.getName());
        Assertions.assertEquals(2000, accountToUpdate.getBalance());
    }

    @Test
    public void testDeleteAccount() {
        // Act
        accountService.delete(accountId);

        // Assert
        Assertions.assertFalse(accountRepository.findById(accountId).isPresent());
    }

    @Test
    public void testFindAllByUserId() {
        // Act
        Collection<Account> accounts = accountService.findAllByUserId(userId);

        // Assert
        Assertions.assertEquals(1, accounts.size());
    }

    @Test
    public void testCreateAccount() {
        // Arrange
        Account newAccount = new Account();
        newAccount.setName("Account2");
        newAccount.setBalance(500);

        // Act
        accountService.create(newAccount, userId);

        // Assert
        Collection<Account> accounts = accountService.findAllByUserId(userId);

        Assertions.assertEquals(2, accounts.size());
        Assertions.assertTrue(accounts.stream().anyMatch(a -> a.getName().equals("Account2")));
    }

    @Test
    public void testFindById() {
        // Act
        Account account = accountService.findById(accountId);

        // Assert
        Assertions.assertEquals("Account", account.getName());
        Assertions.assertEquals(1000, account.getBalance());
    }
}
