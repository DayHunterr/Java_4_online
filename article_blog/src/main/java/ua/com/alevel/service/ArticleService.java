package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.article.Article;

public interface ArticleService extends CrudService<Article> {

    void like(Long id);
    void dislike(Long id);
//    void uploadFile(MultipartFile file, Integer postId);
}
