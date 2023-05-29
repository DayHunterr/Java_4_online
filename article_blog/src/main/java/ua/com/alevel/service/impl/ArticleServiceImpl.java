package ua.com.alevel.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.article.ArticleRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.ArticleService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.dto.PersistenceRequestDTO;

import java.util.List;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Article article) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        article.setUser(user);
        articleRepository.save(article);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(Article article) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Article current = articleRepository.findById(article.getId()).orElse(null);
        validPost(article, user.getId());
        current.setTitle(article.getTitle());
        current.setMessage(article.getMessage());
        current.setComment(article.getComment());
        articleRepository.save(current);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void delete(Long id) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Article article = articleRepository.findById(id).orElse(null);
        validPost(article, user.getId());
        articleRepository.delete(article);
        reactionService.deleteByArticleId(article.getId());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public Article findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        existPost(article);
        return article;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public Page<Article> findAll(PersistenceRequestDTO data) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
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
            return articleRepository.findAllByUserIsNot(user, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long id) {
        reactionProcess(id, true);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long id) {
        reactionProcess(id, false);
    }

    @Override
    public List<Article> listAll(String keyword) {
        if (keyword != null) {
            return articleRepository.search(keyword);
        }
        return articleRepository.findAll();
    }

    private void validPost(Article article, Long personalId) {
        existPost(article);
        if (!article.getUser().getId().equals(personalId)) {
            throw new RuntimeException("you are not a owner");
        }
        if (StringUtils.isBlank(article.getMessage()) || StringUtils.isBlank(article.getTitle())) {
            throw new RuntimeException("message or title can not be empty");
        }
    }

    private void existPost(Article article) {
        if (article == null) {
            throw new RuntimeException("article not found");
        }
    }

    private void reactionProcess(Long postId, boolean status) {
        User user = (User) userRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Article article = articleRepository.findById(postId).orElse(null);
        hasReactionToPost(article, user.getId());
        if (status) {
            reactionService.like(article.getId(), user.getId());
        } else {
            reactionService.dislike(article.getId(), user.getId());
        }
    }

    private void hasReactionToPost(Article article, Long personalId) {
        existPost(article);
        if (article.getUser().getId().equals(personalId)) {
            throw new RuntimeException("you do not have a reaction to this article");
        }
    }
}
