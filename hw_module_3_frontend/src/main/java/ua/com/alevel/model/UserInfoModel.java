package ua.com.alevel.model;


import lombok.Data;

import java.util.Collection;

@Data
public class UserInfoModel {

    private UserModel user;
    private Collection<AccountModel> accounts;

}
