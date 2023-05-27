package ua.com.alevel.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.dto.AuthDTO;

@Service
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserService userService;

    @Override
    public void registration(AuthDTO authData) {
        User user = new User();
        user.setEmail(authData.getEmail());
        user.setPassword(authData.getPassword());
        userService.create(user);
    }
}

