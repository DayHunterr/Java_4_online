package ua.com.alevel.service.impl;
import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.GarageDao;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.service.GarageService;

import java.util.Collection;
import java.util.Optional;
@BeanClass
public class GarageServiceImpl implements GarageService {
    @InjectBean
    private GarageDao garageDao;


    @Override
    public void create(Garage garage) {
        garageDao.create(garage);
    }

    @Override
    public void delete(Long id) {
        garageDao.delete(id);
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