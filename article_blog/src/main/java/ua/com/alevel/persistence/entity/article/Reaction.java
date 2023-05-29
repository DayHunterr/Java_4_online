package ua.com.alevel.persistence.entity.article;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "reactions")
public class Reaction extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @Column(name = "like_post", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean like;

    public Reaction() {
        super();
        this.like = false;
    }
}
