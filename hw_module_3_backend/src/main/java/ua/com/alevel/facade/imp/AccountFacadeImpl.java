package ua.com.alevel.facade.imp;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.AccountInfoDTO;
import ua.com.alevel.dto.AccountTransactionInfoDTO;
import ua.com.alevel.dto.TransactionInfoDTO;
import ua.com.alevel.entity.Account;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.TransactionService;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public void create(Account account, Long id) {
        accountService.create(account,id);
    }

    @Override
    public AccountTransactionInfoDTO findById(Long id) {
        return new AccountTransactionInfoDTO(
                accountService.findById(id),
                transactionService.findAllByAccountId(id)
                        .stream()
                        .map(TransactionInfoDTO::new)
                        .toList()
        );
    }

    @Override
    public Collection<AccountInfoDTO> findAll() {
        return accountService.findAll()
                .stream()
                .map(e -> new AccountInfoDTO(e, transactionService.findAllByAccountId(e.getId()).size()))
                .toList();
    }

    @Override
    public void update(Account entity, Long id) {
        accountService.update(entity,id);
    }

    @Override
    public void delete(long id) {
        accountService.delete(id);
    }
}
