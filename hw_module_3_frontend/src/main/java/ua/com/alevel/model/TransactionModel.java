package ua.com.alevel.model;

import lombok.Data;

@Data
public class TransactionModel {

    private Long id;
    private Integer amount;
    private String type;
    private String accName;

}
