package ua.com.alevel.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.MapUtils;
import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.article.ArticleRepository;
import ua.com.alevel.persistance.repository.user.UserRepository;
import ua.com.alevel.service.ArticleService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.dto.PersistenceRequestData;

import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.SEARCH_MESSAGE_PARAM;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ReactionService reactionService;

    public ArticleServiceImpl(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            ReactionService reactionService) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.reactionService = reactionService;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long id) {
        reactionProcess(id, true);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long id) {
        reactionProcess(id, false);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Article article) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        article.setUser(user);
        articleRepository.save(article);
    }


    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(Article article) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Article currentArticle = articleRepository.findById(article.getId()).orElse(null);
        validationArticle(article, user.getId());
        currentArticle.setTitle(article.getTitle());
        currentArticle.setMessage(article.getMessage());
        currentArticle.setComment(article.getComment());
        articleRepository.save(currentArticle);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void delete(Long id) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Article article = articleRepository.findById(id).orElse(null);
        validationArticle(article, user.getId());
        articleRepository.delete(article);
        reactionService.deleteByArticleId(article.getId());
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(readOnly = true)
    public Article findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article does not exist"));
        existArticle(article);
        return article;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(readOnly = true)
    public Page<Article> findAll(PersistenceRequestData data) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Sort sort = data.getOrder().equals("desc") ? Sort.by(Sort.Order.desc(data.getSort())) : Sort.by(Sort.Order.asc(data.getSort()));
        if (data.isOwner()) {
            return articleRepository.findAllByUser(user, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
        } else {
            Map<String, Object> parameters = data.getParameters();
            if (MapUtils.isNotEmpty(parameters)) {
                String query = (String) parameters.get(SEARCH_MESSAGE_PARAM);
                if (StringUtils.isNotBlank(query)) {
                    return articleRepository.findAllByUserIsNotAndMessageContainingIgnoreCase(user, query, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
                }
            }
            return articleRepository.findAllByUserIsNot(user, PageRequest.of(data.getPage() - 1, data.getSize(), sort)); //????????????????????????????
        }
    }

    private void reactionProcess(Long articleId, boolean status) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Article article = articleRepository.findById(articleId).orElse(null);
        hasReactionToPost(article, user.getId());
        if (status) {
            reactionService.like(article.getId(), user.getId());
        } else {
            reactionService.dislike(article.getId(), user.getId());
        }
    }

    private void hasReactionToPost(Article article, Long userId) {
        existArticle(article);
        if (article.getUser().getId().equals(userId)) {
            throw new RuntimeException("You already have a reaction to this post"); // добавить анлайк
        }
    }

    private void existArticle(Article article) {
        if (article == null) {
            throw new RuntimeException("Article not found!");
        }
    }


    private void validationArticle(Article article, Long userId) {
        existArticle(article);
        if (!article.getUser().getId().equals(userId)) {
            throw new RuntimeException("You don`t have access, because you are not a owner");
        }
        if (StringUtils.isBlank(article.getMessage()) || StringUtils.isBlank(article.getTitle())) {
            throw new RuntimeException("Text or title must not be empty");
        }
    }
}
