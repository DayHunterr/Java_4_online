package ua.com.alevel.persistence.listener;

import org.apache.commons.lang.StringUtils;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class FullNameGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateFullName(User user) {
        if (StringUtils.isBlank(user.getFirstName()) || StringUtils.isBlank(user.getLastName())) {
            user.setFullName("");
            return;
        }
        user.setFullName(user.getFirstName() + " " + user.getLastName());
    }
}
