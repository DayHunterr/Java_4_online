package ua.com.alevel.api;

import ua.com.alevel.model.AccountInfoModel;
import ua.com.alevel.model.AccountModel;

import java.util.Collection;
import java.util.Optional;

public interface AccountApiService {

    Optional<AccountInfoModel> findById(Long Id);
    Collection<AccountModel> findAll();

}
