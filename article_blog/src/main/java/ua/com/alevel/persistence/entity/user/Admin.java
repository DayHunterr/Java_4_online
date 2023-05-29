package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.type.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends BaseUser {

    public Admin() {
        super();
        setRole(Role.ROLE_ADMIN);
    }
}
