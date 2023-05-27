package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.config.SecurityService;
import ua.com.alevel.facade.AuthValidationFacade;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistance.type.Role;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.dto.AuthDTO;

@Controller
@RequiredArgsConstructor
public class AuthController extends BaseController {

     private final RegistrationFacade registrationFacade;
     private final AuthValidationFacade authValidatorFacade;
     private final SecurityService securityService;



    @GetMapping("/")
    public String index(Model model) {
        return redirectProcess(model);
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(Role.ADMIN.name())) {
                return "redirect:/admin/panel";
            }
            if (SecurityUtil.hasRole(Role.USER.name())) {
                return "redirect:/user/panel";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDTO());
        return "/registration";
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(Role.ADMIN.name())) {
            return "redirect:/admin/panel";
        }
        if (SecurityUtil.hasRole(Role.USER.name())) {
            return "redirect:/user/panel";
        }
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDTO authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        registrationFacade.registration(authForm);
        return redirectProcess(model);
    }
}
