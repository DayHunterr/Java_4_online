package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao<C extends BaseEntity> {
    void create(C entity);

    void update(C entity);

    void delete(C entity);

    Optional<C> findById(Long id);

    Collection<C> findAll();

}
