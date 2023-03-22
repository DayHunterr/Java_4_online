package ua.com.alevel.services.Impl;

import ua.com.alevel.dao.CarDao;
import ua.com.alevel.dao.GarageDao;
import ua.com.alevel.dao.Imp.CarDaoImpl;
import ua.com.alevel.dao.Imp.GarageDaoImpl;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.services.CarService;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class CarServiceImpl implements CarService {

    private final CarDao carDao = new CarDaoImpl();
    private final GarageDao garageDao = new GarageDaoImpl();

    @Override
    public void create(Car car) {
        carDao.create(car);
    }

    @Override
    public void update(Car car) {
        carDao.update(car);
    }

    @Override
    public void delete(Car car) {
        carDao.delete(car);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public Collection<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public void changeGarageForCar(Long carId, Long garageId, Long newGarageId) {
        deleteCarFromGarage(carId, garageId);
        insertInGarage(carId, newGarageId);
    }

    @Override
    public void deleteCarFromGarage(Long carId, Long garageID) {
        Car car = carDao.findById(carId).get();
        Garage garage = garageDao.findById(garageID).get();
        Set<Car> cars = garage.getCars();
        cars.removeIf(car1 -> car1.getId().equals(car.getId()));
        garageDao.update(garage);
    }

    @Override
    public void insertInGarage(Long carId, Long garageID) {
        Car car = carDao.findById(carId).get();
        Garage garage = garageDao.findById(garageID).get();
        Set<Car> cars = garage.getCars();
        cars.add(car);
        garageDao.update(garage);
    }

    public Collection<Car> findCarByGarage(Long id) {
        Garage garage = garageDao.findById(id).get();
        Set<Car> cars = garage.getCars();
        return cars;
    }
}
