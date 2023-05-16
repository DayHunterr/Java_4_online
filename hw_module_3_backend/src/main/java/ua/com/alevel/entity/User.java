package ua.com.alevel.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String name;


    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}
