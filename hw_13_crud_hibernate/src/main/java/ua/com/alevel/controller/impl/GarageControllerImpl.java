package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.GarageController;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.services.Impl.CarServiceImpl;
import ua.com.alevel.services.Impl.GarageServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class GarageControllerImpl implements GarageController {

    private GarageServiceImpl garageService = new GarageServiceImpl();

    private CarServiceImpl carService = new CarServiceImpl();

    @Override
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Select your options");
            String select;
            menu();
            while ((select = reader.readLine()) != null) {
                crud(reader, select);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create car, please enter 1");
        System.out.println("If you want find car, please enter 2");
        System.out.println("If you want find all cars, please enter 3");
        System.out.println("If you want update car owner, please enter 4");
        System.out.println("If you want delete car, please enter 5");
        System.out.println("If you want create garage, please enter 6");
        System.out.println("If you want find garage, please enter 7");
        System.out.println("If you want find all garages, please enter 8");
        System.out.println("If you want put car to garage, please enter 9");
        System.out.println("If you want to change garage for car, please enter 10");
        System.out.println("If you want delete car from garage, please enter 11");
        System.out.println("If you want delete garage, please enter 12");
        System.out.println("If you want find cars by garage, please enter 13");
        System.out.println("If you want close application, please enter 14");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) {
        try {
            switch (select) {
                case "1" -> createCar(reader);
                case "2" -> findCarById(reader);
                case "3" -> findAllCars();
                case "4" -> updateCar(reader);
                case "5" -> deleteCar(reader);
                case "6" -> createGarage(reader);
                case "7" -> findGarageById(reader);
                case "8" -> findAllGarages();
                case "9" -> putCarToGarage(reader);
                case "10" -> changeGarageForCar(reader);
                case "11" -> deleteCarFromGarages(reader);
                case "12" -> deleteGarage(reader);
                case "13" -> findCarByGarage(reader);
                case "14" -> stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            menu();
        }
    }

    private void createCar(BufferedReader reader) {
        try {
            System.out.println("Create car");
            Car car = new Car();
            validateCarBrand(reader, car);
            validateCarOwnerName(reader, car);
            validateCarSerialNumber(reader, car);
            carService.create(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createGarage(BufferedReader reader) {
        try {
            System.out.println("Create garage");
            Garage garage = new Garage();
            validateGarageAddress(reader, garage);
            garageService.create(garage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findCarById(BufferedReader reader) {
        try {
            System.out.println("Find car by id");
            System.out.println("Please enter id");
            String id = reader.readLine();
            Optional<Car> car = carService.findById(Long.valueOf(id));
            if (car.isEmpty()) {
                System.out.println("There are nothing to find");
            } else {
                System.out.println("Car = " + car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findGarageById(BufferedReader reader) {
        try {
            System.out.println("Find garage by id");
            System.out.println("Please enter garage id");
            String id = reader.readLine();
            Optional<Garage> garage = garageService.findById(Long.valueOf(id));
            if (garage.isEmpty()) {
                System.out.println("There are nothing to find");
            } else {
                System.out.println("Garage = " + garage.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putCarToGarage(BufferedReader reader) {
        try {
            System.out.println("Put car to garage");
            System.out.println("Please enter garage id");
            Long garageId = Long.valueOf(reader.readLine());
            System.out.println("Please enter car id");
            Long carId = Long.valueOf(reader.readLine());
            carService.insertInGarage(carId, garageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findCarByGarage(BufferedReader reader) {
        try {
            System.out.println("Find all cars by garage");
            System.out.println("Please enter garage id");
            Long garageId = Long.valueOf(reader.readLine());
            Collection<Car> cars = carService.findCarByGarage(garageId);
            cars.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAllCars() {
        try {
            System.out.println("Find all cars");
            List<Car> cars = (List<Car>) carService.findAll();
            if (cars.isEmpty()) {
                System.out.println("There are nothing to find!");
            } else {
                cars.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAllGarages() {
        try {
            System.out.println("Find all garages");
            List<Garage> garages = (List<Garage>) garageService.findAll();
            if (garages.isEmpty()) {
                System.out.println("There are nothing to find!");
            } else {
                garages.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCar(BufferedReader reader) {
        try {
            System.out.println("Update car owner");
            System.out.println("Please enter car id which you want to update");
            String carId = reader.readLine();
            System.out.println("Please enter new car owner name");
            String carOwnerName = reader.readLine();
            Optional<Car> car = carService.findById(Long.valueOf(carId));
            if (car.isPresent()) {
                if (carOwnerName.matches("^[a-zA-Z]{3,15}$")) {
                    car.get().setCarOwnerName(carOwnerName);
                    carService.update(car.get());
                } else System.out.println("Invalid input! Use only letters!");
            } else System.out.println("This id does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCar(BufferedReader reader) {
        try {
            System.out.println("Delete car");
            System.out.println("Please enter car id which you want to delete");
            String carId = reader.readLine();
            Optional<Car> car = carService.findById(Long.valueOf(carId));
            if (car.isPresent()) {
                carService.delete(car.get());
            } else {
                System.out.println("This id does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteGarage(BufferedReader reader) {
        try {
            System.out.println("Delete Garage");
            System.out.println("Please enter garage id which you want to delete");
            String garageId = reader.readLine();
            Optional<Garage> garage = garageService.findById(Long.valueOf(garageId));
            if (garage.isPresent()) {
                garageService.delete(garage.get());
            } else {
                System.out.println("This id does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCarFromGarages(BufferedReader reader) {
        try {
            System.out.println("Delete car from garage");
            System.out.println("Please enter car id car which you want to delete");
            Long carId = Long.valueOf(reader.readLine());
            System.out.println("Please enter garage id from which you want to delete");
            Long garageId = Long.valueOf(reader.readLine());
            carService.deleteCarFromGarage(carId, garageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeGarageForCar(BufferedReader reader) {
        try {
            System.out.println("Change garage for car");
            System.out.println("Enter car id which you want to replace");
            Long carId = Long.valueOf(reader.readLine());
            System.out.println("Enter current garage id ");
            Long oldGarageId = Long.valueOf(reader.readLine());
            System.out.println("Enter new garage id in which you want to replace");
            Long newGarageId = Long.valueOf(reader.readLine());
            carService.changeGarageForCar(carId, oldGarageId, newGarageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateCarBrand(BufferedReader reader, Car car) {
        try {
            String carBrand;
            while (true) {
                System.out.println("Please enter car brand");
                carBrand = reader.readLine();
                if (carBrand.matches("^[a-zA-Z]{3,15}$")) {
                    car.setCarBrand(carBrand);
                    break;
                } else {
                    System.out.println("Wrong input! Use only letters ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateCarOwnerName(BufferedReader reader, Car car) {
        try {
            String carOwnerName;
            while (true) {
                System.out.println("Please enter car owner name");
                carOwnerName = reader.readLine();
                if (carOwnerName.matches("^[a-zA-Z]{3,15}$")) {
                    car.setCarOwnerName(carOwnerName);
                    break;

                } else {
                    System.out.println("Wrong input! Use only letters ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateCarSerialNumber(BufferedReader reader, Car car) {
        try {
            String carSerialNumber;
            while (true) {
                System.out.println("Please enter your car serial number format <XX1234XX> ");
                carSerialNumber = reader.readLine().toUpperCase();
                if (carSerialNumber.matches("^[A-Z]{2}[0-9]{4}[A-Z]{2}$")) {
                    car.setCarSerialNumber(carSerialNumber);
                    break;
                } else {
                    System.out.println("Wrong input!Use only letters and numbers");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateGarageAddress(BufferedReader reader, Garage garage) {
        try {
            String garageAddress;
            while (true) {
                System.out.println("Please enter garage address");
                garageAddress = reader.readLine();
                if (garageAddress.matches("^[a-zA-Z0-9]{3,15}$")) {
                    garage.setAddress(garageAddress);
                    break;
                } else {
                    System.out.println("Wrong input! Use only letters and numbers");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        System.exit(0);
    }
}
