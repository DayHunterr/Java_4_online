package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.pages.Reaction;

import java.util.List;

public interface ReactionService {

    void deleteByArticleId(Long postId);
    void like(Long articleId, Long userId);
    void dislike(Long articleId, Long userId);
    List<Reaction> findAllByArticleIdAndLikeTrue(Long articleId);
    List<Reaction> findAllByArticleIdAndLikeFalse(Long articleId);

    /*Map<String, List<KeyValueData<Date, Long>>> generateChartByPostReaction();*/
}
