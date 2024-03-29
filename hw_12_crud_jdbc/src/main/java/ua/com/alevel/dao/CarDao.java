package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.util.Collection;
import java.util.Optional;

public interface CarDao {
    void create(Car car);
    void insertInGarage(Car car, Garage garage);
    void update(Car car);
    void delete(Long id);
    void deleteFromGarage(Long id);
    Optional<Car> findById(Long id);
    Collection<Car> findAll();
    Collection<Car> findByGarage(Long depId);
}
