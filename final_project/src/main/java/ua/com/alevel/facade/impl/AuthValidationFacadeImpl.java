package ua.com.alevel.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.facade.AuthValidationFacade;
import ua.com.alevel.service.UserService;
import ua.com.alevel.dto.AuthDTO;

@Service
@RequiredArgsConstructor
public class AuthValidationFacadeImpl implements AuthValidationFacade {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthDTO data = (AuthDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (data.getEmail().length() < 6 || data.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.Email.IncorrectSize");
        }

        if (userService.existsByEmail(data.getEmail())) {
            errors.rejectValue("email", "Exist.Email.EmailAlreadyExist");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (data.getPassword().length() < 6 || data.getPassword().length() > 16) {
            errors.rejectValue("password", "Size.Password.IncorrectPasswordSize");
        }

        if (!data.getPasswordConfirm().equals(data.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.Password.PasswordDoesntMatch");
        }
    }
}
