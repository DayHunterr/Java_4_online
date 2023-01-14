package ua.com.alevel.db;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;


public class DbGarage {
    private static List<Car> cars = new ArrayList<>();
    private static List<Garage> garages = new ArrayList<>();
    private static DbGarage instance;

    private DbGarage() {
    }

    public static DbGarage getInstance() {
        if (instance == null) {
            instance = new DbGarage();
        }
        return instance;
    }

    private String generateCarId() {
        String id = RandomStringUtils.randomNumeric(4);
        if (cars.stream().anyMatch(car -> car.getId().equals(id))) {
            return generateCarId();
        }
        return id;
    }

    private String generateGarageId() {
        String id = RandomStringUtils.randomNumeric(4);
        if (garages.stream().anyMatch(garage -> garage.getId().equals(id))) {
            return generateGarageId();
        }
        return id;
    }

    public void addCar(Car car) {
        car.setId(generateCarId());
        cars.add(car);
    }

    public void addGarage(Garage garage) {
        garage.setId(generateGarageId());
        garages.add(garage);
    }

    public Optional<Car> getCar(String id) {
        return cars
                .stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    public Optional<Garage> getGarage(String id) {
        return garages
                .stream()
                .filter(garage -> garage.getId().equals(id))
                .findFirst();
    }

    public void addCarToGarage(String carId, String garageId) {
        Optional<Car> optionalCar = getCar(carId);
        Optional<Garage> optionalGarage = getGarage(garageId);
        if (optionalCar.isPresent() && optionalGarage.isPresent()) {
            Car car = optionalCar.get();
            Garage garage = optionalGarage.get();
            for (Garage garage_i : garages) {
                if (garage_i.getCarIdList().contains(carId)) {
                    System.out.println("This car already in another garage");
                    return;
                }
                car.getGarageIdList().add(garageId);
                garage.getCarIdList().add(carId);
            }
        }
    }

    public List<Car> findByGarage(String garageId) {
        List<Car> cars = new ArrayList<>();
        if (getGarage(garageId).isPresent()) {
            Set<String> carsIds = getGarage(garageId).get().getCarIdList();
            for (String carId : carsIds) {
                if (getCar(carId).isPresent()) {
                    cars.add(getCar(carId).get());
                }
            }
        }
        return cars;
    }

    public void deleteCar(String id) {
        garages.stream().map(Garage::getCarIdList).forEach(car -> car.remove(id));
        cars.removeIf(car -> car.getId().equals(id));
    }

    public void deleteCarFromGarage(String carId, String garageId) {
        Optional<Car> optionalCar = getCar(carId);
        Optional<Garage> optionalGarage = getGarage(garageId);
        if (optionalCar.isPresent() && optionalGarage.isPresent()) {
            Garage garage = optionalGarage.get();
            Car car = optionalCar.get();
            garage.getCarIdList().remove(carId);
            car.getGarageIdList().remove(garageId);
        }
    }

    public void deleteGarage(String id) {
        cars.stream().map(Car::getGarageIdList).forEach(garage -> garage.remove(id));
        garages.removeIf(garage -> garage.getId().equals(id));
    }

    public void updateCar(String carId, String carOwnerName) {
        Optional<Car> optionalCar = getCar(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setCarOwnerName(carOwnerName);
        }
    }

    public List<Car> findAllCars() {
        return cars;
    }

    public List<Garage> findAllGarages() {
        return garages;
    }
}