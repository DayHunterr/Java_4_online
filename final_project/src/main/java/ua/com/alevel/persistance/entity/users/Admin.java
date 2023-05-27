package ua.com.alevel.persistance.entity.users;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistance.type.Role;

@Getter
@Setter
@Entity
public class Admin extends User{

    public Admin(){
        super();
        setRoleType(Role.ADMIN);
    }

}
