package ua.com.alevel.facade.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.dto.*;
import ua.com.alevel.facade.ArticleFacade;
import ua.com.alevel.persistence.entity.article.Article;
import ua.com.alevel.persistence.entity.article.Reaction;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.service.ArticleService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.ArticleUtil;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleFacadeImpl implements ArticleFacade {

    private final ArticleService articleService;
    private final ReactionService reactionService;
    private final UserService userService;


    public ArticleFacadeImpl(
            ArticleService articleService,
            ReactionService reactionService,
            UserService userService){
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

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }

    @Override
    public PageDTO<ArticleDTO> findAll(WebRequest request) {
        PersistenceRequestDTO persistenceRequestDTO = new PersistenceRequestDTO(request);
        Page<Article> page = articleService.findAll(persistenceRequestDTO);
        PageDTO<ArticleDTO> data = new PageDTO<>();
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getNumber());
        data.setTotalElements(page.getTotalPages());
        data.setTotalPages(page.getTotalPages());
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<ArticleDTO> list = page.getContent().stream().map(ArticleDTO::new).collect(Collectors.toList());
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
//            List<Long> ids = likeReactionList.stream().map(Reaction::getUser).map(User::getId).collect(Collectors.toList());
            List<Long> ids = new ArrayList<>();
            for (Reaction reaction : likeReactionList) {
                User user = reaction.getUser();
                if (user != null) {
                    ids.add(user.getId());
                }
            }
            generatePostResponseData(data, ids, null);
        }
        if (CollectionUtils.isNotEmpty(dislikeReactionList)) {
//            List<Long> ids = dislikeReactionList.stream().map(Reaction::getUser).map(User::getId).collect(Collectors.toList());
            List<Long> ids = new ArrayList<>();
            for (Reaction reaction : dislikeReactionList) {
                User user = reaction.getUser();
                if (user != null) {
                    ids.add(user.getId());
                }
            }
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



//    @Override
//    public UserDashboardChartData generatePersonalDashboardChartData() {
//        UserDashboardChartData data = new UserDashboardChartData();
//        Map<String, List<KeyValueDTO<Date, Long>>> chartDataMap = reactionService.generateChartByArticleReaction();
//        if (MapUtils.isEmpty(chartDataMap)) {
//            return data;
//        }
//
//        List<KeyValueDTO<Date, Long>> allPostData = chartDataMap.get(ArticleUtil.POST_ALL);
//        List<KeyValueDTO<Date, Long>> likePostData = chartDataMap.get(ArticleUtil.LIKE_ALL);
//        List<KeyValueDTO<Date, Long>> dislikePostData = chartDataMap.get(ArticleUtil.DISLIKE_ALL);
//
//        List<Long> allPost = allPostData.stream().map(KeyValueDTO::getValue).collect(Collectors.toUnmodifiableList());
//        List<Long> likePost = new ArrayList<>();
//        List<Long> dislikePost = new ArrayList<>();
//
//        List<Date> dates = allPostData.stream().map(KeyValueDTO::getKey).collect(Collectors.toUnmodifiableList());
//
//        KeyValueDTO<Date, Long> keyValue;
//        for (Date reactionDate : dates) {
//            keyValue = likePostData.stream().filter(keyValueDTO -> keyValueDTO.getKey().getTime() == reactionDate.getTime()).findAny().orElse(null);
//            if (keyValue == null) {
//                likePost.add(0L);
//            } else {
//                likePost.add(keyValue.getValue());
//            }
//            keyValue = dislikePostData.stream().filter(keyValueDTO -> keyValueDTO.getKey().getTime() == reactionDate.getTime()).findAny().orElse(null);
//            if (keyValue == null) {
//                dislikePost.add(0L);
//            } else {
//                dislikePost.add(keyValue.getValue());
//            }
//        }
//
//        data.setLabels(dates);
//        data.setAllPost(allPost);
//        data.setLikePost(likePost);
//        data.setDislikePost(dislikePost);
//
//        return data;
//    }



    private void generatePostResponseData(ArticleResponseDTO data, List<Long> likeIds, List<Long> dislikeIds) {
        List<User> users;
        Map<Long, String> map;
        if (likeIds != null) {
            users = userService.findAllByListId(likeIds);
            map = users.stream().collect(Collectors.toMap(User::getId, User::getFullName));
            data.setLikes(map);
        }
        if (dislikeIds != null) {
            users = userService.findAllByListId(dislikeIds);
            map = users.stream().collect(Collectors.toMap(User::getId, User::getFullName));
            data.setDislikes(map);
        }
    }
}
