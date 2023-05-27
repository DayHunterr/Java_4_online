package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.users.User;

import java.util.List;

public interface UserService extends BaseUserService<User>{
    boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findAllByListId(List<Long> ids);
}
