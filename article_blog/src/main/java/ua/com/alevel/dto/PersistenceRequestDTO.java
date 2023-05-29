package ua.com.alevel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.util.WebRequestUtil;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PersistenceRequestDTO {

    private int page;
    private int size;
    private String sort;
    private String order;
    private boolean owner;
    private Map<String, Object> parameters;

    public PersistenceRequestDTO(WebRequest request) {
        PageAndSizeDTO pageAndSizeDTO = WebRequestUtil.generatePageAndSizeData(request);
        SortDTO sortDTO = WebRequestUtil.generateSortData(request);
        this.page = pageAndSizeDTO.getPage();
        this.size = pageAndSizeDTO.getSize();
        this.sort = sortDTO.getSort();
        this.order = sortDTO.getOrder();
        this.owner = WebRequestUtil.getOwner(request);
        this.parameters = WebRequestUtil.generateParameters(request);
    }
}
