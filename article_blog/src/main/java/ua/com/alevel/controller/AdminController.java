package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserFacade userFacade;

    public AdminController(UserService userService, UserFacade userFacade) {
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @GetMapping("/panel")
    public String index() {
        return "pages/admin/panel";
    }

    @GetMapping("/users")
    public String allUsers(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "pages/admin/users_all";
    }

    @GetMapping("/block/{id}")
    public String isEnabled(@PathVariable Long id){
        boolean isEnabled = userFacade.setEnabled(id,false);
        return "redirect:/admin/users";
    }

    @GetMapping("/unblock/{id}")
    public String isDisabled(@PathVariable Long id){
        boolean isEnabled = userFacade.setEnabled(id,true);
        return "redirect:/admin/users";
    }
}
