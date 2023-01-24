package ua.com.alevel.controller;

import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;
import ua.com.alevel.service.GarageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class GarageController {

    private GarageService garageService = new GarageService();

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
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

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> create(reader);
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
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create car");
        Car car = new Car();
        validateCarBrand(reader, car);
        validateCarOwnerName(reader, car);
        validateCarSerialNumber(reader, car);
        garageService.addCar(car);
    }

    private void createGarage(BufferedReader reader) throws IOException {
        System.out.println("Create garage");
        Garage garage = new Garage();
        validateGarageAddress(reader, garage);
        garageService.addGarage(garage);
    }

    private void findCarById(BufferedReader reader) throws IOException {
        System.out.println("Find car by id");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Optional<Car> car = garageService.getCar(id);
        if (car.isEmpty()) {
            System.out.println("There are nothing to find");
        } else {
            System.out.println("Car = " + car);
        }
    }

    private void findGarageById(BufferedReader reader) throws IOException {
        System.out.println("Find garage by id");
        System.out.println("Please enter garage id");
        String id = reader.readLine();
        Optional<Garage> garage = garageService.getGarage(id);
        if (garage.isEmpty()) {
            System.out.println("There are nothing to find");
        } else {
            System.out.println("Garage = " + garage);
        }
    }

    private void putCarToGarage(BufferedReader reader) throws IOException {
        System.out.println("Put car to garage");
        System.out.println("Please enter garage id");
        String garageId = reader.readLine();
        System.out.println("Please enter car id");
        String carId = reader.readLine();
        if (garageService.getCar(carId).isEmpty() && garageService.getGarage(garageId).isEmpty()) {
            System.out.println("This ids does not exist");
        } else {
            garageService.addCarToGarage(carId, garageId);
        }
    }

    private void findCarByGarage(BufferedReader reader) throws IOException {
        System.out.println("Find all cars by garage");
        System.out.println("Please enter garage id");
        String garageId = reader.readLine();
        List<Car> cars = garageService.findByGarage(garageId);
        if (cars.isEmpty()) {
            System.out.println("There are nothing to find!");
        } else {
            cars.forEach(System.out::println);
        }
    }

    private void findAllCars() {
        System.out.println("Find all cars");
        List<Car> cars = garageService.findAllCars();
        if (cars.isEmpty()) {
            System.out.println("There are nothing to find!");
        } else {
            cars.forEach(System.out::println);
        }
    }

    private void findAllGarages() {
        System.out.println("Find all garages");
        List<Garage> garages = garageService.findAllGarages();
        if (garages.isEmpty()) {
            System.out.println("There are nothing to find!");
        } else {
            garages.forEach(System.out::println);
        }
    }

    private void updateCar(BufferedReader reader) throws IOException {
        System.out.println("Update car owner");
        System.out.println("Please enter car id which you want to update");
        String carId = reader.readLine();
        System.out.println("Please enter new car owner name");
        String carOwnerName = reader.readLine();
        if (garageService.getCar(carId).isPresent()) {
            if (carOwnerName.matches("^[a-zA-Z]{3,15}$")) {
                garageService.updateCar(carId, carOwnerName);
            } else System.out.println("Invalid input! Use only letters!");
        } else System.out.println("This id does not exist");
    }

    private void deleteCar(BufferedReader reader) throws IOException {
        System.out.println("Delete car");
        System.out.println("Please enter car id which you want to delete");
        String carId = reader.readLine();
        if (garageService.getCar(carId).isPresent()) {
            garageService.deleteCar(carId);
        } else {
            System.out.println("This id does not exist");
        }
    }

    private void deleteGarage(BufferedReader reader) throws IOException {
        System.out.println("Delete Garage");
        System.out.println("Please enter garage id which you want to delete");
        String garageId = reader.readLine();
        if (garageService.getGarage(garageId).isPresent()) {
            garageService.deleteGarage(garageId);
        } else {
            System.out.println("This id does not exist");
        }
    }

    private void deleteCarFromGarages(BufferedReader reader) throws IOException {
        System.out.println("Delete car from garage");
        System.out.println("Please enter car id car which you want to delete");
        String carId = reader.readLine();
        System.out.println("Please enter garage id from which you want to delete");
        String garageId = reader.readLine();
        if (garageService.getCar(carId).isPresent() && garageService.getGarage(garageId).isPresent()) {
            garageService.deleteCarFromGarage(carId, garageId);
        } else {
            System.out.println("This ids does not exist");
        }
    }

    private void validateCarBrand(BufferedReader reader, Car car) throws IOException {
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
    }

    private void validateCarOwnerName(BufferedReader reader, Car car) throws IOException {
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
    }

    private void validateCarSerialNumber(BufferedReader reader, Car car) throws IOException {
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
    }

    private void validateGarageAddress(BufferedReader reader, Garage garage) throws IOException {
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
    }

    private void changeGarageForCar(BufferedReader reader) throws IOException {
        System.out.println("Change garage for car");
        System.out.println("Enter car id which you want to replace");
        String carNum = reader.readLine();
        System.out.println("Enter current garage id ");
        String oldGarageNum = reader.readLine();
        System.out.println("Enter new garage id in which you want to replace");
        String garageNum = reader.readLine();
        if (garageService.getCar(carNum).isPresent() && garageService.getGarage(oldGarageNum).isPresent() && garageService.getGarage(garageNum).isPresent()) {
            garageService.deleteCarFromGarage(carNum, oldGarageNum);
            garageService.addCarToGarage(carNum, garageNum);
        } else {
            System.out.println("This ids does not exist");
        }
    }

    private void stop() {
        System.exit(0);
    }
}