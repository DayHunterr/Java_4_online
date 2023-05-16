package ua.com.alevel.service;

import ua.com.alevel.entity.Account;

import java.util.Collection;

public interface AccountService extends BaseEntityService<Account> {

    void create(Account account,Long id);
    void update(Account account,Long id);
    void delete(Long id);
    Collection<Account> findAllByUserId(Long id);

}
