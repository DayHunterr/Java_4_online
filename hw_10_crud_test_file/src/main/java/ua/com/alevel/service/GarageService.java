package ua.com.alevel.service;
import ua.com.alevel.dao.GarageDao;
import ua.com.alevel.dao.GarageDao1;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GarageService {
    private GarageDao garageDao = new GarageDao1();

    public GarageService() {
    }
    public void addCar(Car car)  {
        garageDao.addCar(car);
    }
    public void addGarage(Garage garage){
        garageDao.addGarage(garage);
    }
    public Optional<Car> getCar(String id) {
        return garageDao.getCar(id);
    }
    public Optional<Garage> getGarage(String id) {
        return garageDao.getGarage(id);
    }
    public void addCarToGarage(String carId, String garageId){
        garageDao.addCarToGarage(carId, garageId);
    }
    public List<Car> findByGarage(String garageId) {
        return garageDao.findByGarage(garageId);
    }
    public void deleteCar(String id) {
        garageDao.deleteCar(id);
    }
    public void deleteCarFromGarage(String carId, String garageId){
        garageDao.deleteCarFromGarage(carId, garageId);
    }
    public void deleteGarage(String id) {
        garageDao.deleteGarage(id);
    }
    public void updateCar(String carId, String carOwnerName){
        garageDao.updateCar(carId,carOwnerName);
    }
    public List<Car> findAllCars() {
        return garageDao.findAllCars();
    }
    public List<Garage> findAllGarages(){
        return garageDao.findAllGarages();
    }
}