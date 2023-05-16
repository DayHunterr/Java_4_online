package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransactionCreatedDTO {


    private String description;

    private Integer amount;

    private Long senderAccId;

    private Long receiverAccId;
}