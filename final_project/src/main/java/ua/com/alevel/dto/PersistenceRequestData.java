package ua.com.alevel.dto;

import org.springframework.web.context.request.WebRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.util.WebRequestUtil;


import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PersistenceRequestData {

    private int page;
    private int size;
    private String sort;
    private String order;
    private boolean owner;
    private Map<String, Object> parameters;

    public PersistenceRequestData(WebRequest request) {
        PageSizeDTO pageSizeDTO = WebRequestUtil.generatePageSizeDTO(request);
        SortDTO sortDTO = WebRequestUtil.generateSortData(request);
        this.page = pageSizeDTO.getPage();
        this.size = pageSizeDTO.getSize();
        this.sort = sortDTO.getSort();
        this.order = sortDTO.getOrder();
        this.owner = WebRequestUtil.getOwner(request);
        this.parameters = WebRequestUtil.generateParameters(request);
    }
}