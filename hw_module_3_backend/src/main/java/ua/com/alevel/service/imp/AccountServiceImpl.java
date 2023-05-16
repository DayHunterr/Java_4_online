package ua.com.alevel.service.imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.entity.Account;
import ua.com.alevel.exeption.CustomException;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactionRepository;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.AccountService;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;



    @Override
    @Transactional
    public void update(Account account, Long id){
        validateAccountData(account);
        if(accountRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Entity doesn't exist");
        } else {
            account.setId(id);
            account.setUser(accountRepository.findById(id).get().getUser());
        }
    }

    @Override
    @Transactional
    public void delete(Long id){
        if(accountRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Entity doesn't exist");
        } else {
            transactionRepository.findAllByAccountId(id).forEach(e -> transactionRepository.deleteById(e.getId()));
            accountRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Collection<Account> findAllByUserId(Long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Entity doesn't exist");
        }
        Collection<Account> accounts = accountRepository.findAllByUserId(id);
        return Objects.requireNonNullElse(accounts, Collections.emptyList());
    }

    @Override
    @Transactional
    public void create(Account entity,Long id) {
        validateAccountData(entity);
        if(userRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Entity doesn't exist");
        } else {
            entity.setUser(userRepository.findById(id).get());
            accountRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exist"));
    }

    @Override
    @Transactional
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    private void validateAccountData(Account entity) {
        if (entity.getBalance() == null || entity.getBalance() < 0) {
            throw new CustomException("Invalid balance number");
        }
        if (entity.getName() == null || entity.getName().equals("")) {
            throw new CustomException("Invalid data");
        }
    }
}
