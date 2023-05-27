package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.dto.PersistenceRequestData;

public interface ArticleService extends CrudService<Article>{
    void like(Long id);
    void dislike(Long id);
    Page<Article> findAll(PersistenceRequestData data);
}
