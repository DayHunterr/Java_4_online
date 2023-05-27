package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.ArticleFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.dto.ArticleDTO;

@Controller
@RequestMapping("/user/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleFacade articleFacade;


    @GetMapping("/all")
    public String findAllArticle(WebRequest request, Model model) {
        model.addAttribute("pageData", articleFacade.findAll(request));
        return "pages/user/article/article_all";
    }

    @GetMapping("/my")
    public String findMyArticle(WebRequest request, Model model) {
        model.addAttribute("pageData", articleFacade.findAll(request));
        return "pages/user/article/article_my";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id, @RequestParam("reaction") boolean reaction) {
        model.addAttribute("articleFullData", articleFacade.findById(id));
        model.addAttribute("reaction", reaction);
        return "pages/user/article/article_details";
    }

    @GetMapping("/new")
    public String redirectToNewPage(Model model) {
        model.addAttribute("articleForm", new ArticleDTO());
        return "pages/user/article/article_new";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("articleForm", articleFacade.findById(id));
        return "pages/user/article/article_update";
    }

    @PostMapping("/new")
    public String newArticle(@ModelAttribute("articleForm") ArticleDTO data) {
        articleFacade.create(data);
        return "redirect:/user/article/my";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleFacade.delete(id);
        return "redirect:/user/article/my";
    }

    @PostMapping("/update")
    public String updateArticle(@ModelAttribute("articleForm") ArticleDTO data, @RequestParam Long id) {
        articleFacade.update(data, id);
        return "redirect:/user/article/my";
    }

    @GetMapping("/like/{id}")
    public String likeArticle(@PathVariable Long id) {
        articleFacade.like(id);
        return "redirect:/user/article/details/" + id + "?reaction=true";
    }

    @GetMapping("/dislike/{id}")
    public String dislikeArticle(@PathVariable Long id) {
        articleFacade.dislike(id);
        return "redirect:/user/article/details/" + id + "?reaction=true";
    }

    @PostMapping("/search")
    private String searchBooks(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebRequestUtil.SEARCH_MESSAGE_PARAM, query);
        ra.addAttribute("owner", false);
        return "redirect:/user/article/all";
    }

}
