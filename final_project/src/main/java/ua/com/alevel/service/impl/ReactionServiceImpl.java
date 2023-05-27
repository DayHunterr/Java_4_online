package ua.com.alevel.service.impl;

import org.apache.commons.collections.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.persistance.entity.pages.Reaction;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.article.ArticleRepository;
import ua.com.alevel.persistance.repository.article.ReactionRepository;
import ua.com.alevel.persistance.repository.user.UserRepository;
import ua.com.alevel.service.ReactionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void deleteByArticleId(Long postId) {
        Article article = articleRepository.findById(postId).orElseThrow(() -> new RuntimeException("Article not found"));
        List<Reaction> reactions = reactionRepository.findAllByArticle(article);
        if (CollectionUtils.isNotEmpty(reactions)) {
            reactionRepository.deleteAll(reactions);
        }
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long postId, Long personalId) {
        Article article = articleRepository.findById(postId).orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(personalId).orElseThrow(() -> new RuntimeException("User not found"));
        reactionProcess(article, user, true);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long postId, Long personalId) {
        Article article = articleRepository.findById(postId).orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(personalId).orElseThrow(() -> new RuntimeException("User not found"));
        reactionProcess(article, user, false);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByArticleIdAndLikeTrue(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        return reactionRepository.findAllByArticleAndLikeTrue(article);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByArticleIdAndLikeFalse(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        return reactionRepository.findAllByArticleAndLikeFalse(article);
    }

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
}
