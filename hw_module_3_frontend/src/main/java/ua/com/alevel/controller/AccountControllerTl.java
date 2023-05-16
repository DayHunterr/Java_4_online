package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.api.AccountApiService;


@Controller
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountControllerTl {

    private final AccountApiService accountApiService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("accounts", accountApiService.findAll());
        return "profile/accounts";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        if(accountApiService.findById(id).isPresent()){
            model.addAttribute("account", accountApiService.findById(id).get());
            return "profile_details/account_details";
        }
        return "errors/something_going_wrong";
    }
}
