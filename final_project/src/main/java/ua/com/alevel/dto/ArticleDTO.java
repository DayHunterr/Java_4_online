package ua.com.alevel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistance.entity.pages.Article;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {

    private Long id;
    private String title;
    private String message;
    private String comment;
    private Date created;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.comment = article.getComment();
        this.message = article.getMessage();
        this.created = article.getCreated();
    }
}
