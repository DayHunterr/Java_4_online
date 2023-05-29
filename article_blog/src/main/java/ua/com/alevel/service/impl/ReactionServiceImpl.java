package ua.com.alevel.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.article.Reaction;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.article.ArticleRepository;
import ua.com.alevel.persistence.repository.article.ReactionRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.ArticleUtil;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.dto.KeyValueDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ReactionServiceImpl(
            ReactionRepository reactionRepository,
            UserRepository userRepository,
            ArticleRepository articleRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void deleteByArticleId(Long articleIds) {
        Article article = articleRepository.findById(articleIds).orElseThrow(() -> new RuntimeException("article not found"));
        List<Reaction> reactions = reactionRepository.findAllByArticle(article);
        if (CollectionUtils.isNotEmpty(reactions)) {
            reactionRepository.deleteAll(reactions);
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long articleIds, Long userId) {
        Article article = articleRepository.findById(articleIds).orElseThrow(() -> new RuntimeException("article not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        reactionProcess(article, user, true);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long articleIds, Long userId) {
        Article article = articleRepository.findById(articleIds).orElseThrow(() -> new RuntimeException("article not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        reactionProcess(article, user, false);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByArticleIdAndLikeTrue(Long articleIds) {
        Article article = articleRepository.findById(articleIds).orElseThrow(() -> new RuntimeException("article not found"));
        return reactionRepository.findAllByArticleAndLikeTrue(article);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByArticleIdAndLikeFalse(Long articleIds) {
        Article article = articleRepository.findById(articleIds).orElseThrow(() -> new RuntimeException("article not found"));
        return reactionRepository.findAllByArticleAndLikeFalse(article);
    }

//    @Override
//    @PreAuthorize("hasRole('USER')")
//    @Transactional(readOnly = true)
//    public Map<String, List<KeyValueDTO<Date, Long>>> generateChartByArticleReaction() {
//        List<Long> articleIds = generateAllPostIdListByPersonal();
//        if (CollectionUtils.isEmpty(articleIds)) {
//            return Collections.emptyMap();
//        }
//        Map<String, List<KeyValueDTO<Date, Long>>> chartDataMap = new HashMap<>();
//        chartDataMap.put(ArticleUtil.POST_ALL, reactionRepository.generateAllArticleWithoutReaction(articleIds));
//        chartDataMap.put(ArticleUtil.LIKE_ALL, reactionRepository.generateAllArticleByReaction(articleIds, true));
//        chartDataMap.put(ArticleUtil.DISLIKE_ALL, reactionRepository.generateAllArticleByReaction(articleIds, false));
//        return chartDataMap;
//    }

    private void reactionProcess(Article article, User user, boolean status) {
        Reaction reaction = reactionRepository.findByArticleAndUser(article, user);
        if (reaction == null) {
            reaction = new Reaction();
            reaction.setUser(user);
            reaction.setArticle(article);
        }
        reaction.setLike(status);
        reactionRepository.save(reaction);
    }

    private List<Long> generateAllPostIdListByPersonal() {
        User user = (User) userRepository.findByEmail(SecurityUtil.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Article> articles = articleRepository.findAllByUser(user);
        if (CollectionUtils.isEmpty(articles)) {
            return Collections.emptyList();
        }
        return articles.stream().map(Article::getId).collect(Collectors.toList());
    }
}
