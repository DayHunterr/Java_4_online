package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.article.Article;

import java.util.List;

public interface ArticleService extends CrudService<Article> {

    void like(Long id);
    void dislike(Long id);
    List<Article> listAll(String keyword);
    List<Article> lastArticles();
}
