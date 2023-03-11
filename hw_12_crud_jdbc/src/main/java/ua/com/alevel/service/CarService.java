package ua.com.alevel.service;

import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.util.Collection;
import java.util.Optional;

public interface CarService {
    void create(Car entity);

    void insertInGarage(Car car, Garage garage);

    void update(Car entity);

    void delete(Long id);

    void deleteFromGarage(Long id);

    void changeGarageForCar(Car car, Garage oldGarage, Garage newGarage);

    Optional<Car> findById(Long id);

    Collection<Car> findAll();

    Collection<Car> findByGarage(Long depId);
}
