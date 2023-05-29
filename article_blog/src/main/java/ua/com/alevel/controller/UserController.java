package ua.com.alevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.alevel.facade.ArticleFacade;
import ua.com.alevel.dto.UserDashboardChartData;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ArticleFacade articleFacade;

    public UserController(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }

    @GetMapping
    public String index() {
        return "pages/user/panel";
    }

    @GetMapping("/panel")
    public String panel() {
        return "pages/user/panel";
    }

//    @GetMapping("/panel/chart")
//    public @ResponseBody ResponseEntity<UserDashboardChartData> generateChart() {
//        return ResponseEntity.ok(articleFacade.generatePersonalDashboardChartData());
//    }
}
