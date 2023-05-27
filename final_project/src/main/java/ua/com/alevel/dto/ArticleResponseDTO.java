package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistance.entity.pages.Article;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ArticleResponseDTO extends ArticleDTO {

    private Map<Long, String> likes;
    private Map<Long, String> dislikes;

    public ArticleResponseDTO(Article article) {
        super(article);
        this.likes = new HashMap<>();
        this.dislikes = new HashMap<>();
    }
}
