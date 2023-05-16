package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.UserApiService;

import ua.com.alevel.model.AccountPostModel;
import ua.com.alevel.model.UserModel;

import java.util.Collection;


@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserControllerTl {
    private final UserApiService userApiService;

    @GetMapping("/new")
    public String createUserMenu(Model model){
        model.addAttribute("user", new UserModel());
        return "new/user_new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute UserModel user){
        if(!userApiService.create(user)){
            return "errors/something_going_wrong";
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}/new")
    public String createAccountMenu(@PathVariable Long id, Model model){
        model.addAttribute("account", new AccountPostModel());
        model.addAttribute("owner_id",id);
        return "new/account_new";
    }

    @PostMapping("/{id}/new")
    public String createAccount(@PathVariable Long id,@ModelAttribute AccountPostModel account){
        if(!userApiService.createAccount(account,id)){
            return "errors/something_going_wrong";
        }
        return "redirect:/users/" + id;
    }

    @GetMapping
    public String findAll(Model model){
        Collection<?> users = userApiService.findAll();
        model.addAttribute("users", users);
        return "profile/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,Model model){
        if(userApiService.findById(id).isPresent()){
            model.addAttribute("user_details",userApiService.findById(id).get());
            return "profile_details/user_details";
        }
        return "errors/something_going_wrong";
    }
}
