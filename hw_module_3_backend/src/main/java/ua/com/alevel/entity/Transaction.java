package ua.com.alevel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction extends BaseEntity {

    @Column
    private String description;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private CategoryType category;

    @Column(name = "amount")
    private Integer amount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
