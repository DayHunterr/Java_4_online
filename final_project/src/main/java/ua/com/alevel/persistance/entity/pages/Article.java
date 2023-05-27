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
@Table(name = "articles")
public class Article extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    private User user;

    public Article() {
        super();
    }
}
