package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.BaseUser;

public interface BaseUserService<U extends BaseUser> extends CrudService<U> {
}
