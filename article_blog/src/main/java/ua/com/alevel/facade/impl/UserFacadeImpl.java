package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.service.UserService;
@Service

public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean setEnabled(Long id, boolean enable) {
        return userService.isEnable(id,enable);
    }
}
