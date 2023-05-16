package ua.com.alevel.repository;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends BaserEntityRepository<Account> {
    List<Account> findAllByUserId(Long userId);
}
