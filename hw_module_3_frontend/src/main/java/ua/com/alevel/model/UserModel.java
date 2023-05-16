package ua.com.alevel.model;

import lombok.Data;
import java.util.Date;

@Data
public class UserModel {

    private Long id;
    private Date created;
    private String name;
}
