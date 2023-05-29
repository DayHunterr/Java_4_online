package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.article.Reaction;
import ua.com.alevel.dto.KeyValueDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReactionService {

    void deleteByArticleId(Long postId);
    void like(Long rostId, Long personalId);
    void dislike(Long rostId, Long personalId);
    List<Reaction> findAllByArticleIdAndLikeTrue(Long postId);
    List<Reaction> findAllByArticleIdAndLikeFalse(Long postId);
//    Map<String, List<KeyValueDTO<Date, Long>>> generateChartByArticleReaction();
}
