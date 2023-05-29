package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.BaseUser;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface BaseUserRepository<U extends BaseUser> extends BaseRepository<U> {

    Optional<BaseUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
