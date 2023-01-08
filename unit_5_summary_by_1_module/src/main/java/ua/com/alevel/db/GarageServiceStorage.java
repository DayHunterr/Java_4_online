package ua.com.alevel.db;

import org.apache.commons.lang3.RandomStringUtils;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;


import java.util.*;

public class GarageServiceStorage {

    private static List<Car> cars = new ArrayList<>();
    private static  List<Garage> garages = new ArrayList<>();

    private GarageServiceStorage() {}

    private static String generateCarId() {

        String id = RandomStringUtils.randomNumeric(4);
        if (cars.stream().anyMatch(car -> car.getId().equals(id))) {
            return generateCarId();
        }

        return id;
    }

    private static String generateGarageId() {
        String id = RandomStringUtils.randomNumeric(4);
        if (garages.stream().anyMatch(garage -> garage.getId().equals(id))) {
            return generateGarageId();
        }

        return id;
    }

    public static void addCar(Car car) {
        car.setId(generateCarId());
        cars.add(car);
    }

    public static void addGarage(Garage garage) {
        garage.setId(generateGarageId());
        garages.add(garage);
    }

    public static Car getCar(String id) {
        return cars.
                stream().
                filter(car -> car.getId().equals(id)).
                findFirst()
                .orElse(null);
    }
    public static Garage getGarage(String id) {
        return garages.
                stream().
                filter(garage -> garage.getId().equals(id)).
                findFirst()
                .orElse(null);
    }


        public static void addCarToGarage (String carId, String garageId){
            Car car = getCar(carId);
            Garage garage = getGarage(garageId);
            for (Garage garage_i : garages) {
                if (garage_i.getCarIdList().contains(carId)) {
                    System.out.println("This car already in another garage");
                    return;
                }

                    car.getGarageIdList().add(garageId);
                    garage.getCarIdList().add(carId);

            }
        }

    public static List<Car> findByGarage(String garageId) {
        List<Car> cars = new ArrayList<>();
        if (getGarage(garageId) != null) {
            Garage garage = getGarage(garageId);
            Set<String> carsIds = garage.getCarIdList();
            for (String carId : carsIds) {
                Car car = getCar(carId);
                if (car != null) {
                    cars.add(car);
                }
            }
        }
        return cars;
    }

    public static boolean deleteCar(String id) {

        garages.stream().map(Garage::getCarIdList).forEach(car -> car.remove(id));

        return cars.removeIf(car -> car.getId().equals(id));
    }
    public static boolean deleteCarFromGarage(String carNum, String garageNum) {
        Garage garage = getGarage(garageNum);
        Car car = getCar(carNum);
        garage.getCarIdList().remove(carNum);
        return car.getGarageIdList().remove(garageNum);

    }
    public static boolean deleteGarage(String id) {

        cars.stream().map(Car::getGarageIdList).forEach(garage -> garage.remove(id));

        return garages.removeIf(garage -> garage.getId().equals(id));

    }
    public static void updateCar(String carId, String carOwnerName) {
            Car car = getCar(carId);
            car.setCarOwnerName(carOwnerName);
    }





    public static List<Car> findAllCars() {
        return cars;
    }
    public static List<Garage> findAllGarages() {
        return garages;
    }

}
