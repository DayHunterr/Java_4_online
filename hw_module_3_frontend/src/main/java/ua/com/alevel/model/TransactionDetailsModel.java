package ua.com.alevel.model;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDetailsModel extends TransactionModel {

    private Date created;
    private String description;
    private Long accId;
    private Long ownerId;
    private String ownerName;

}
