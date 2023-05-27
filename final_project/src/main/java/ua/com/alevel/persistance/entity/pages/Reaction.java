package ua.com.alevel.persistance.entity.pages;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistance.entity.BaseEntity;
import ua.com.alevel.persistance.entity.users.User;

@Getter
@Setter
@Entity
@Table(name = "reactions")
public class Reaction extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @Column(name = "like_post", nullable = false)
    private Boolean like;

    public Reaction() {
        super();
        this.like = false;
    }
}
