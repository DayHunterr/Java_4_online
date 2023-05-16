package ua.com.alevel.facade;

import ua.com.alevel.entity.BaseEntity;

public interface BaseEntityFacade<ENTITY extends BaseEntity> {
    void update(ENTITY entity,Long id);
    void delete(long id);
}
