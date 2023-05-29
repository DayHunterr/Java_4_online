package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.dto.PersistenceRequestDTO;

public interface CrudService<AE extends BaseEntity> {

    void create(AE ae);
    void update(AE ae);
    void delete(Long id);
    AE findById(Long id);
    Page<AE> findAll(PersistenceRequestDTO persistenceRequestDTO);
}
