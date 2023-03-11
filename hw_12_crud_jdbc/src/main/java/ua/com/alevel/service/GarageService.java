package ua.com.alevel.service;

import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GarageService {

    void create(Garage entity);

    void delete(Long id);

    Optional<Garage> findById(Long id);

    Collection<Garage> findAll();
}
