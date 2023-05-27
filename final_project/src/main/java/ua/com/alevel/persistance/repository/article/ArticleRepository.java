package ua.com.alevel.persistance.repository.article;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.BaseRepository;

import java.util.List;


@Repository
public interface ArticleRepository extends BaseRepository<Article> {
    List<Article> findAllByUser(User user);
    Page<Article> findAllByUser(User user, Pageable pageable);
    Page<Article> findAllByUserIsNot(User user, Pageable pageable); //?
    Page<Article> findAllByUserIsNotAndMessageContainingIgnoreCase(User user, String message, Pageable pageable); //?
}
