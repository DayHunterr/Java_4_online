package ua.com.alevel.persistence.repository.article;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.article.Reaction;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface ReactionRepository extends BaseRepository<Reaction> {

    List<Reaction> findAllByArticle(Article article);
    List<Reaction> findAllByArticleAndLikeTrue(Article article);
    List<Reaction> findAllByArticleAndLikeFalse(Article article);
    Reaction findByArticleAndUser(Article article, User user);


}
