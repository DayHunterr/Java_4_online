package ua.com.alevel.model;

import lombok.Data;

@Data
public class AccountModel {

    private Long id;

    private Integer balance;

    private String name;

    private Integer transactionNumber;

    private Long owner;

}
