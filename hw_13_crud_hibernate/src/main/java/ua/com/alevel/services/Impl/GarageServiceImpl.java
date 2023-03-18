package ua.com.alevel.services.Impl;

import ua.com.alevel.dao.Imp.GarageDaoImpl;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.services.GarageService;

import java.util.Collection;
import java.util.Optional;

public class GarageServiceImpl implements GarageService {

    private GarageDaoImpl garageDao = new GarageDaoImpl();

    @Override
    public void create(Garage garage) {
        garageDao.create(garage);
    }

    @Override
    public void update(Garage garage) {
        garageDao.update(garage);
    }

    @Override
    public void delete(Garage garage) {
        garageDao.delete(garage);
    }

    @Override
    public Optional<Garage> findById(Long id) {
        return garageDao.findById(id);
    }

    @Override
    public Collection<Garage> findAll() {
        return garageDao.findAll();
    }
}
