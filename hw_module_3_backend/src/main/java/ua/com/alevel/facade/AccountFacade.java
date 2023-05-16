package ua.com.alevel.facade;

import ua.com.alevel.dto.AccountInfoDTO;
import ua.com.alevel.dto.AccountTransactionInfoDTO;
import ua.com.alevel.entity.Account;

import java.util.Collection;
import java.util.Collections;

public interface AccountFacade extends BaseEntityFacade<Account>{

    void create(Account account,Long id);

    AccountTransactionInfoDTO findById(Long id);

    Collection<AccountInfoDTO> findAll();

}
