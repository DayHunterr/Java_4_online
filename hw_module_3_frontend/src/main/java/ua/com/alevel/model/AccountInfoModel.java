package ua.com.alevel.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class AccountInfoModel {

    private Long accId;

    private Date accCreated;

    private Integer balance;

    private String name;

    private Long userId;

    private List<TransactionModel> transactions;

}
