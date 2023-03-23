package ua.com.alevel.services;

import ua.com.alevel.entity.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarService {

    void create(Car car);
    void update(Car car);
    void delete(Car car);
    Optional<Car> findById(Long id);
    Collection<Car> findAll();
    void deleteCarFromGarage(Long carId, Long garageId);
    void insertInGarage(Long carId, Long garageID);
    Collection<Car> findCarByGarage(Long id);
    void changeGarageForCar(Long carId, Long garageId, Long newGarageId);
}
