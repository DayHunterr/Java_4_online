package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.BaseUser;

public interface BaseUserService<U extends BaseUser> extends CrudService<U> { }
