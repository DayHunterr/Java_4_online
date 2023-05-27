package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.BaseEntity;

public interface CrudService<BE extends BaseEntity> {
    void create(BE be);
    void update(BE be);
    void delete(Long id);
    BE findById(Long id);

    /*Page<BE> findAll(PersistenceRequestData persistenceRequestData);*/
}
