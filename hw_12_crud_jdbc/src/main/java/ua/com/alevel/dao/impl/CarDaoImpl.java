package ua.com.alevel.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@BeanClass
public class CarDaoImpl implements CarDao {
    private static final String CREATE_CAR = "insert into cars values (default, CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(), ?, ?, ?)";
    private static final String INSERT_CAR_IN_GARAGE = "insert into cars_in_garages values (?, ?)";
    private static final String UPDATE_CAR = "update cars set updated = CURRENT_TIMESTAMP(),car_brand = ?, car_owner_name = ?, car_serial_number = ? where id = ?";
    private static final String DELETE_CAR = "delete from cars where id = ?";
    private static final String DELETE_CAR_FROM_GARAGE = "delete from cars_in_garages as cig where cig.car_id = ?";
    private static final String FIND_CAR_BY_ID = "select * from cars where id = ";
    private static final String FIND_ALL_CARS = "select * from cars";
    private static final String FIND_ALL_CARS_BY_GARAGE = "select id, car_brand, car_owner_name, car_serial_number from cars left join cars_in_garages cig on cig.car_id = cars.id where cig.garage_id = ";


    @InjectBean
    private JdbcService jdbcService;

    @Override
    public void create(Car car) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_CAR)) {
            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarOwnerName());
            preparedStatement.setString(3, car.getCarSerialNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }
    @Override
    public void insertInGarage(Car car, Garage garage) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_CAR_IN_GARAGE)) {
            preparedStatement.setLong(1,car.getId());
            preparedStatement.setLong(2,garage.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void update(Car car) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(UPDATE_CAR)) {
            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarOwnerName());
            preparedStatement.setString(3, car.getCarSerialNumber());
            preparedStatement.setLong(4, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_CAR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }
    @Override
    public void deleteFromGarage(Long id) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_CAR_FROM_GARAGE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Optional<Car> findById(Long id) {
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_CAR_BY_ID + id)) {
           if (resultSet.next()) {
                return Optional.of(generateCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_CARS)) {
            while (resultSet.next()) {
                cars.add(generateCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return cars;
        }
        return cars;
    }

    public Collection<Car> findByGarage(Long garageId) {
        List<Car> cars = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_CARS_BY_GARAGE + garageId)) {
            while (resultSet.next()) {
                cars.add(generateCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return cars;
        }
        return cars;
    }

    private Car generateCarByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String carBrand = resultSet.getString("car_brand");
        String carOwnerName = resultSet.getString("car_owner_name");
        String carSerialNumber = resultSet.getString("car_serial_number");
        Car car = new Car();
        car.setId(id);
        car.setCarBrand(carBrand);
        car.setCarOwnerName(carOwnerName);
        car.setCarSerialNumber(carSerialNumber);
        return car;
    }
}