package ua.com.alevel.persistance.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistance.entity.BaseUser;

import java.util.Optional;

@Repository
public interface BaseUserRepository <U extends BaseUser> extends BaseRepository<U> {

    Optional<BaseUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
