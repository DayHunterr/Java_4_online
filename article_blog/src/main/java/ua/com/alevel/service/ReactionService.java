package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.article.Reaction;

import java.util.List;

public interface ReactionService {

    void deleteByArticleId(Long postId);
    void like(Long rostId, Long personalId);
    void dislike(Long rostId, Long personalId);
    List<Reaction> findAllByArticleIdAndLikeTrue(Long postId);
    List<Reaction> findAllByArticleIdAndLikeFalse(Long postId);
}
