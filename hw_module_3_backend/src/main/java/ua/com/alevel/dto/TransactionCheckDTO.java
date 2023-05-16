package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.CategoryType;

import java.util.Date;

@Getter
@Setter
public class TransactionCheckDTO {

    private Long id;

    private String description;

    private Integer amount;

    private CategoryType type;

    private Date created;

}
