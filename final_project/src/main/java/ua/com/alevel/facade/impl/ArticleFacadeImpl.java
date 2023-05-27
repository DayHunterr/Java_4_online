package ua.com.alevel.facade.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.ArticleFacade;
import ua.com.alevel.persistance.entity.pages.Article;
import ua.com.alevel.persistance.entity.pages.Reaction;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.service.ArticleService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.dto.ArticleDTO;
import ua.com.alevel.dto.ArticleResponseDTO;
import ua.com.alevel.dto.PageDTO;
import ua.com.alevel.dto.PersistenceRequestData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleFacadeImpl implements ArticleFacade {

    private final ArticleService articleService;
    private final ReactionService reactionService;
    private final UserService userService;

    public ArticleFacadeImpl(ArticleService articleService, ReactionService reactionService, UserService userService) {
        this.articleService = articleService;
        this.reactionService = reactionService;
        this.userService = userService;
    }

    @Override
    public void create(ArticleDTO data) {
        Article article = new Article();
        article.setMessage(data.getMessage());
        article.setTitle(data.getTitle());
        article.setComment(data.getComment());
        articleService.create(article);
    }

    @Override
    public void update(ArticleDTO data, Long id) {
        Article article = articleService.findById(id);
        article.setMessage(data.getMessage());
        article.setTitle(data.getTitle());
        article.setComment(data.getComment());
        articleService.update(article);

    }
    //      ⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠋⠉⠁⠄⠄⠈⠙⠻⣿⣿⣿⣿
    //      ⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠙⢿⣿
    //      ⣿⣿⣿⣿⡿⠃⠄⠄⠄⢀⣀⣀⡀⠄⠄⠄⠄⠄⠄⠄⠈⢿
    //      ⣿⣿⣿⡟⠄⠄⠄⠄⠐⢻⣿⣿⣿⣷⡄⠄⠄⠄⠄⠄⠄⠈
    //      ⣿⣿⣿⠃⠄⠄⠄⢀⠴⠛⠙⣿⣿⡿⣿⣦⠄⠄⠄⠄⠄⠄
    //      ⣿⣿⠃⠄⢠⡖⠉⠄⠄⠄⣠⣿⡏⠄⢹⣿⠄⠄⠄⠄⠄⢠
    //      ⣿⠃⠄⠄⢸⣧⣤⣤⣤⢾⣿⣿⡇⠄⠈⢻⡆⠄⠄⠄⠄⣾
    //      ⠁⠄⠄⠄⠈⠉⠛⢿⡟⠉⠉⣿⣷⣀⠄⠄⣿⡆⠄⠄⢠⣿
    //      ⠄⠄⠄⠄⠄⠄⢠⡿⠿⢿⣷⣿⣿⣿⣿⣿⠿⠃⠄⠄⣸⣿
    //      ⠄⠄⠄⠄⠄⢀⡞⠄⠄⠄⠈⣿⣿⣿⡟⠁⠄⠄⠄⠄⣿⣿
    //      ⠄⠄⠄⠄⠄⢸⠄⠄⠄⠄⢀⣿⣿⡟⠄⠄⠄⠄⠄⢠⣿⣿
    //      ⠄⠄⠄⠄⠄⠘⠄⠄⠄⢀⡼⠛⠉⠄⠄⠄⠄⠄⠄⣼⣿⣿
    //      ⠄⠄⠄⠄⠄⡇⠄⠄⢀⠎⠄⠄⠄⠄⠄⠄⠄⠄⠄⠙⢿⣿
    //      ⠄⠄⠄⠄⢰⠃⠄⢀⠎⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠙

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }

    @Override
    public PageDTO<ArticleDTO> findAll(WebRequest request) {
        PersistenceRequestData persistenceRequestData = new PersistenceRequestData(request);
        Page<Article> page = articleService.findAll(persistenceRequestData);
        PageDTO<ArticleDTO> data = new PageDTO<>();
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getNumber());
        data.setTotalElements(page.getTotalPages());
        data.setTotalPages(page.getTotalPages());
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<ArticleDTO> list = page.getContent()
                    .stream()
                    .map(ArticleDTO::new)
                    .collect(Collectors.toList());
            data.setItems(list);
        }
        return data;
    }

    @Override
    public ArticleResponseDTO findById(Long id) {
        Article article = articleService.findById(id);
        List<Reaction> likeReactionList = reactionService.findAllByArticleIdAndLikeTrue(article.getId());
        List<Reaction> dislikeReactionList = reactionService.findAllByArticleIdAndLikeFalse(article.getId());
        ArticleResponseDTO data = new ArticleResponseDTO(article);

        if (CollectionUtils.isNotEmpty(likeReactionList)) {
            List<Long> ids = likeReactionList
            .stream()
            .map(Reaction::getUser)
            .map(User::getId)
            .collect(Collectors.toList());

            generatePostResponseData(data, ids, null);
        }

        if (CollectionUtils.isNotEmpty(dislikeReactionList)) {
            List<Long> ids = dislikeReactionList
            .stream()
            .map(Reaction::getUser)
            .map(User::getId)
            .collect(Collectors.toList());

            generatePostResponseData(data, null, ids);
        }
        return data;
    }

    @Override
    public void like(Long id) {
        articleService.like(id);
    }

    @Override
    public void dislike(Long id) {
        articleService.dislike(id);
    }

    private void generatePostResponseData(ArticleResponseDTO data, List<Long> likeIds, List<Long> dislikeIds) {
        List<User> users;
        Map<Long, String> map;
        if (likeIds != null) {
            users = userService.findAllByListId(likeIds);
            map = users.stream().collect(Collectors.toMap(User::getId, User::getFirstName));
            data.setLikes(map);
        }
        if (dislikeIds != null) {
            users = userService.findAllByListId(dislikeIds);
            map = users.stream().collect(Collectors.toMap(User::getId, User::getFirstName));
            data.setDislikes(map);
        }
    }
}
