package ua.com.alevel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/humans")
public class HumanController {

    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("humans", humanService.getAll());
        return "humans";
    }

    @GetMapping("/new")
    public String newHuman(Model model) {
        model.addAttribute("human", new Human());
        return "new_human";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        this.humanService.delete(id);
        return "redirect:/humans";
    }

    @PostMapping("/new")
    public String createHuman(@ModelAttribute Human human) {
        this.humanService.create(human);
        return "redirect:/humans";
    }
}
