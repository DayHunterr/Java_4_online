package ua.com.alevel.persistence.repository.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.article.Reaction;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.dto.KeyValueDTO;

import java.util.Date;
import java.util.List;

@Repository
public interface ReactionRepository extends BaseRepository<Reaction> {

    List<Reaction> findAllByArticle(Article article);
    List<Reaction> findAllByArticleAndLikeTrue(Article article);
    List<Reaction> findAllByArticleAndLikeFalse(Article article);
    Reaction findByArticleAndUser(Article article, User user);

//    @Query("select new ua.com.alevel.dto.KeyValueDTO(reaction.created, count(reaction.like)) " +
//            "from Reaction as reaction where reaction.article.id in :acticleIds " +
//            "group by reaction.created order by reaction.created asc ")
//    List<KeyValueDTO<Date, Long>> generateAllArticleWithoutReaction(@Param("articleIds") List<Long> postIds);
//
//    @Query("select new ua.com.alevel.dto.KeyValueDTO(reaction.created, count(reaction.like)) " +
//            "from Reaction as reaction where reaction.article.id in :articleIds and reaction.like = :react " +
//            "group by reaction.created order by reaction.created asc ")
//    List<KeyValueDTO<Date, Long>> generateAllArticleByReaction(@Param("articleIds") List<Long> postIds, @Param("react") Boolean react);
}
