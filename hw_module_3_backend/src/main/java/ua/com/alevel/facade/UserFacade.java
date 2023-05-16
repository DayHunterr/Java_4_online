package ua.com.alevel.facade;

import ua.com.alevel.dto.UserAccountsNumberDTO;
import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.dto.UserInfoDTO;
import ua.com.alevel.entity.User;

import java.util.Collection;

public interface UserFacade extends BaseEntityFacade<User>{

    void create(UserCreatedDTO entity);

    Collection<User> findAll();

    Collection<UserAccountsNumberDTO> findAllUsersWithNumberOfAccount();

    UserInfoDTO findById(Long id);
}
