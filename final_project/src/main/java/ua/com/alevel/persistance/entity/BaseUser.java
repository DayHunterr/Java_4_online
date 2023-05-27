package ua.com.alevel.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistance.type.Role;

@Getter
@Setter
@Entity
@Table(name = "users")
public class BaseUser extends BaseEntity {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE", nullable = false)
    private Role roleType;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    public BaseUser() {
        super();
        this.enabled = true;
    }
}
