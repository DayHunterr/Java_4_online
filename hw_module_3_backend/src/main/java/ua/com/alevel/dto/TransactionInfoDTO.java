package ua.com.alevel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.CategoryType;
import ua.com.alevel.entity.Transaction;

@Getter
@Setter
public class TransactionInfoDTO {

    private Long id;

    private Integer amount;

    private CategoryType type;

    private String accName;

    public TransactionInfoDTO(Transaction transaction){

        this.id = transaction.getId();

        this.amount = transaction.getAmount();

        this.type = transaction.getCategory();

        this.accName = transaction.getAccount().getName();

    }
}
