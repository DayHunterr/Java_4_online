package ua.com.alevel.facade.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.AccountInfoDTO;
import ua.com.alevel.dto.UserAccountsNumberDTO;
import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.dto.UserInfoDTO;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.service.UserService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public void update(User entity, Long id) {
        userService.update(entity,id);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public void create(UserCreatedDTO entity) {
        userService.create(entity);
    }

    @Override
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Collection<UserAccountsNumberDTO> findAllUsersWithNumberOfAccount() {
        return userService.findAll()
                .stream()
                .map(e -> new UserAccountsNumberDTO(e, accountService.findAllByUserId(e.getId()).size()))
                .toList();
    }

    @Override
    public UserInfoDTO findById(Long id) {
        return new UserInfoDTO(
                userService.findById(id),
                accountService.findAllByUserId(id)
                        .stream()
                        .map(e -> new AccountInfoDTO(e,transactionService.findAllByAccountId(e.getId()).size()))
                        .toList()
        );
    }
}
