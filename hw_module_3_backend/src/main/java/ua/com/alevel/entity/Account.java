package ua.com.alevel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class Account extends BaseEntity {

    @Column(name = "account_name")
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private Integer balance;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
}
