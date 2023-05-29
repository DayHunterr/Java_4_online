package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;

@Repository
public interface UserRepository extends BaseUserRepository<User> { }
