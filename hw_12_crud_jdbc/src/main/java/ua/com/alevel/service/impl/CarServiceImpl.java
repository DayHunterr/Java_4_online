package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.service.CarService;

import java.util.Collection;
import java.util.Optional;

@BeanClass
public class CarServiceImpl implements CarService {
    @InjectBean
    private CarDao carDao;

    @Override
    public void create(Car car) {
        carDao.create(car);
    }

    @Override
    public void insertInGarage(Car car, Garage garage) {
        carDao.insertInGarage(car,garage);
    }

    @Override
    public void update(Car car) {
        carDao.update(car);
    }

    @Override
    public void delete(Long id) {
        carDao.delete(id);
    }

    @Override
    public void deleteFromGarage(Long id) {
        carDao.deleteFromGarage(id);
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
    public Collection<Car> findByGarage(Long id) {
        return carDao.findByGarage(id);
    }
    @Override
    public void changeGarageForCar(Car car, Garage oldGarage,Garage newGarage) {
        carDao.deleteFromGarage(car.getId());
        carDao.insertInGarage(car,newGarage);
    }
}
