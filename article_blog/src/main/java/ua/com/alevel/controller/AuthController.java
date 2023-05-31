package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.config.SecurityService;
import ua.com.alevel.facade.AuthValidatorFacade;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.dto.AuthDTO;

@Controller
public class AuthController extends BaseController {

    private final SecurityService securityService;
    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;

    public AuthController(SecurityService securityService, RegistrationFacade registrationFacade, AuthValidatorFacade authValidatorFacade) {
        this.securityService = securityService;
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
    }

    @GetMapping("/")
    public String index(Model model) {
        return redirectProcess(model);
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(Role.ROLE_ADMIN.name())) {
                return "redirect:/admin/panel";
            }
            if (SecurityUtil.hasRole(Role.ROLE_USER.name())) {
                return "redirect:/user/panel";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        showMessage(model, false);
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDTO());
        return "/registration";
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(Role.ROLE_ADMIN.name())) {
            return "redirect:/admin/panel";
        }
        if (SecurityUtil.hasRole(Role.ROLE_USER.name())) {
            return "redirect:/user/panel";
        }
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDTO authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }
}
