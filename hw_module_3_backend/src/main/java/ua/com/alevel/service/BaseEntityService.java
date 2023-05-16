package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface BaseEntityService<ENTITY extends BaseEntity> {

    ENTITY findById(Long id);

    Collection<ENTITY> findAll();
}
