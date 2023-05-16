package ua.com.alevel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public BaseEntity() {
        this.created = new Date();
    }
}
