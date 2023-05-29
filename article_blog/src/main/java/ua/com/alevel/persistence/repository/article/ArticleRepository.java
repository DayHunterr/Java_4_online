package ua.com.alevel.persistence.repository.article;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface ArticleRepository extends BaseRepository<Article> {

    List<Article> findAllByUser(User user);
    Page<Article> findAllByUser(User user, Pageable pageable);
    Page<Article> findAllByUserIsNot(User user, Pageable pageable);
    Page<Article> findAllByUserIsNotAndMessageContainingIgnoreCase(User  user, String message, Pageable pageable);
    @Query("SELECT a FROM Article a WHERE a.title LIKE ?1%")
    List<Article> search(String keyword);
}
