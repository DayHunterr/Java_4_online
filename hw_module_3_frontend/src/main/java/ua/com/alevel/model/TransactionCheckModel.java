package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionCheckModel {
    private Long id;

    private String description;

    private Integer amount;

    private String type;

    private Date created;
}
