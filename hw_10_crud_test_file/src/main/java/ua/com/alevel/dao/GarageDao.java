package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.util.List;
import java.util.Optional;

public interface  GarageDao {
    void addCar(Car car);

    void addGarage(Garage garage);

    Optional<Car> getCar(String id);

    Optional<Garage> getGarage(String id);

    void addCarToGarage(String carId, String garageId);

    List<Car> findByGarage(String garageId);

    void deleteCar(String id);

    void deleteCarFromGarage(String carId, String garageId);

    void deleteGarage(String id);

    void updateCar(String carId, String carOwnerName);

    List<Car> findAllCars();

    List<Garage> findAllGarages();
}