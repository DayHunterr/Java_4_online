package ua.com.alevel.service;

import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.entity.User;

public interface UserService extends BaseEntityService<User>{

    void create(UserCreatedDTO user);
    void update(User userUpdated, Long id);
    void delete(Long id);

}
