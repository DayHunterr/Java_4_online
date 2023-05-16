package ua.com.alevel.dto;

import lombok.Data;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;

@Data
public class AccountInfoDTO {

    private Long id;

    private Integer balance;

    private String name;

    private Integer transactionNumber;

    private Long owner;

    public AccountInfoDTO(Account account, Integer number){

        this.id = account.getId();

        this.balance = account.getBalance();

        this.name = account.getName();

        this.transactionNumber = number;

        this.owner = account.getUser().getId();

    }
}
