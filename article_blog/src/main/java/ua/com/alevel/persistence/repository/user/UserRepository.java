package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;

import java.util.List;


@Repository
public interface UserRepository extends BaseUserRepository<User> {
    List<User> findAll();

    @Modifying
    @Query("update BaseUser u set u.enabled = ?2 where u.id = ?1")
    void updateUserEnabledById(Long id, boolean enable);
}
