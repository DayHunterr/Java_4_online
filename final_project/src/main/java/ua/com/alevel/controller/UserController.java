package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public String index() {
        return "pages/user/panel";
    }

    @GetMapping("/panel")
    public String panel() {
        return "pages/user/panel";
    }

}
