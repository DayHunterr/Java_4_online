package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.service.ArticleService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ArticleService articleService;

    public UserController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String index() {
        return "pages/user/panel";
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        List<Article> articles = articleService.lastArticles();
        model.addAttribute("last3Articles", articles);
        return "pages/user/panel";
    }
}
