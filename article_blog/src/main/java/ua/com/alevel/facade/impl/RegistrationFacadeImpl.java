package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.dto.AuthDTO;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserService userService;

    public RegistrationFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registration(AuthDTO authDTO) {
        User user = new User();
        user.setEmail(authDTO.getEmail());
        user.setPassword(authDTO.getPassword());
        userService.create(user);
    }
}
