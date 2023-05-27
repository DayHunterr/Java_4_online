package ua.com.alevel.persistance.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.BaseUserRepository;

@Repository
public interface UserRepository extends BaseUserRepository<User> {
}
