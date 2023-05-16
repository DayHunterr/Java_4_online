package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.entity.BaseEntity;

@NoRepositoryBean
public interface BaserEntityRepository <ENTITY extends BaseEntity> extends JpaRepository<ENTITY,Long> {

}
