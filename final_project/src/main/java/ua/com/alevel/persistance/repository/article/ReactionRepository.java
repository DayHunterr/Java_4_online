package ua.com.alevel.persistance.repository.article;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.persistance.entity.pages.Reaction;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.BaseRepository;

import java.util.List;

@Repository
public interface ReactionRepository extends BaseRepository<Reaction> {
    List<Reaction> findAllByArticle(Article article);
    List<Reaction> findAllByArticleAndLikeTrue(Article article);
    List<Reaction> findAllByArticleAndLikeFalse(Article article);
    Reaction findByArticleAndUser(Article article, User user);

  /*  @Query("select new ua.com.alevel.web.data.KeyValueData(reaction.created, count(reaction.like)) " +
            "from Reaction as reaction where reaction.post.id in :postIds " +
            "group by reaction.created order by reaction.created asc ")
    List<KeyValueData<Date, Long>> generateAllPostWithoutReaction(@Param("postIds") List<Long> postIds);

    @Query("select new ua.com.alevel.web.data.KeyValueData(reaction.created, count(reaction.like)) " +
            "from Reaction as reaction where reaction.post.id in :postIds and reaction.like = :react " +
            "group by reaction.created order by reaction.created asc ")
    List<KeyValueData<Date, Long>> generateAllPostByReaction(@Param("postIds") List<Long> postIds, @Param("react") Boolean react);*/
}
