package ua.com.alevel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.entity.User;

import java.util.Date;

@Getter
@Setter
public class TransactionDetailsDTO extends TransactionInfoDTO{
    private Date created;
    private String description;
    private Long accId;
    private Long ownerId;
    private String ownerName;
    public TransactionDetailsDTO(Transaction transaction){

        super(transaction);

        this.created = transaction.getCreated();

        this.description = transaction.getDescription();

        this.accId = transaction.getAccount().getId();

        this.ownerId = transaction.getAccount().getUser().getId();

        this.ownerName = transaction.getAccount().getUser().getName();

    }
}
