package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.dto.PageDTO;
import ua.com.alevel.dto.UserDashboardChartData;
import ua.com.alevel.dto.ArticleDTO;
import ua.com.alevel.dto.ArticleResponseDTO;

public interface ArticleFacade {

    void create(ArticleDTO data);
    void update(ArticleDTO data, Long id);
    void delete(Long id);
    PageDTO<ArticleDTO> findAll(WebRequest request);
    ArticleResponseDTO findById(Long id);
    void like(Long id);
    void dislike(Long id);

//    UserDashboardChartData generatePersonalDashboardChartData();

}
