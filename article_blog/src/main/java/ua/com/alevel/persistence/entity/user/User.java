package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.listener.AgeByBirthDayGenerationListener;
import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.Role;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue("USER")
@EntityListeners({
        FullNameGenerationListener.class,
        AgeByBirthDayGenerationListener.class
})
public class User extends BaseUser {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    public User() {
        super();
        setRole(Role.ROLE_USER);
    }
}
