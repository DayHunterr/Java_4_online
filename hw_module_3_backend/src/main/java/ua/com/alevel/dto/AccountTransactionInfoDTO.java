package ua.com.alevel.dto;

import lombok.Data;
import ua.com.alevel.entity.Account;

import java.util.Date;
import java.util.List;


@Data
public class AccountTransactionInfoDTO {

    private Long accId;

    private Date accCreated;

    private Integer balance;

    private String name;

    private Long userId;

    private List<TransactionInfoDTO> transactions;

    public AccountTransactionInfoDTO(Account account, List<TransactionInfoDTO> transactions){

        this.accId = account.getId();

        this.accCreated = account.getCreated();

        this.balance = account.getBalance();

        this.name = account.getName();

        this.userId = account.getUser().getId();

        this.transactions = transactions;

    }
}
