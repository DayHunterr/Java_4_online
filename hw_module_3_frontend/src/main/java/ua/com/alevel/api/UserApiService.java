package ua.com.alevel.api;

import ua.com.alevel.model.*;

import java.util.Collection;
import java.util.Optional;

public interface UserApiService {

    Boolean createAccount(AccountPostModel account, Long id);
    Boolean create(UserModel user);
    Optional<UserInfoModel> findById(Long id);
    Collection<UserAccountsNumberModel> findAll();

}
