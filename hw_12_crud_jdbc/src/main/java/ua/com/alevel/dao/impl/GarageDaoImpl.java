package ua.com.alevel.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.dao.GarageDao;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@BeanClass
public class GarageDaoImpl implements GarageDao {


    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_GARAGE = "insert into garages values (default,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),?)";
    private static final String DELETE_GARAGE = "delete from garages where id = ?";
    private static final String FIND_GARAGE_BY_ID = "select * from garages where id = ";
    private static final String FIND_ALL_GARAGES = "select * from garages";


    @Override
    public void create(Garage garage) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_GARAGE)) {
            preparedStatement.setString(1, garage.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_GARAGE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }


    @Override
    public Optional<Garage> findById(Long id) {
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_GARAGE_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(generateCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Garage> findAll() {
        List<Garage> garages = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_GARAGES)) {
            while (resultSet.next()) {
                garages.add(generateCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return garages;
        }
        return garages;
    }
    private Garage generateCarByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String address = resultSet.getString("address");
        Garage garage = new Garage();
        garage.setId(id);
        garage.setAddress(address);
        return garage;
    }
}