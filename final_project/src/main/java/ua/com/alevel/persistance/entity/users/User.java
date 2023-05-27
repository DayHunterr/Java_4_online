package ua.com.alevel.persistance.entity.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistance.entity.BaseUser;
import ua.com.alevel.persistance.type.Role;

import java.util.Date;

@Getter
@Setter
@Entity
public class User extends BaseUser {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    public User() {
        super();
        setRoleType(Role.USER);
    }

}
