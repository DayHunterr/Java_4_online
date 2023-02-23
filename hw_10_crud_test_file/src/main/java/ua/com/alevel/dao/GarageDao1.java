package ua.com.alevel.dao;

import ua.com.alevel.db.DbGarage;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GarageDao1 implements GarageDao {
    DbGarage dbGarage = DbGarage.getInstance();

    public GarageDao1() {
    }

    @Override
    public void addCar(Car car) {
        dbGarage.addCar(car);
    }

    @Override
    public void addGarage(Garage garage) {
        dbGarage.addGarage(garage);
    }

    @Override
    public Optional<Car> getCar(String id) {
        return dbGarage.getCar(id);
    }

    @Override
    public Optional<Garage> getGarage(String id) {
        return dbGarage.getGarage(id);
    }

    @Override
    public void addCarToGarage(String carId, String garageId) {
        dbGarage.addCarToGarage(carId, garageId);
    }

    @Override
    public List<Car> findByGarage(String garageId) {
        return dbGarage.findByGarage(garageId);
    }

    @Override
    public void deleteCar(String id) {
        dbGarage.deleteCar(id);
    }

    @Override
    public void deleteCarFromGarage(String carId, String garageId) {
        dbGarage.deleteCarFromGarage(carId, garageId);
    }

    @Override
    public void deleteGarage(String id) {
        dbGarage.deleteGarage(id);
    }

    @Override
    public void updateCar(String carId, String carOwnerName) {
        dbGarage.updateCar(carId, carOwnerName);
    }

    @Override
    public List<Car> findAllCars() {
        return dbGarage.findAllCars();
    }

    @Override
    public List<Garage> findAllGarages() {
        return dbGarage.findAllGarages();
    }
}