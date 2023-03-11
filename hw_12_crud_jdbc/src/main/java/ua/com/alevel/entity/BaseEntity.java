package ua.com.alevel.entity;

import java.util.Date;

public abstract class BaseEntity {
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private Date created;
    private Date updated;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
    }

}
