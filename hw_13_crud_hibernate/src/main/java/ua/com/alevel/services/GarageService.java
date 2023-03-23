package ua.com.alevel.services;

import ua.com.alevel.entity.Garage;

import java.util.Collection;
import java.util.Optional;

public interface GarageService {

    void create(Garage garage);
    void update(Garage garage);
    void delete(Garage garage);
    Optional<Garage> findById(Long id);
    Collection<Garage> findAll();
}
