package ua.com.alevel.db;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;


public class DbGarage {
    private static final File CARS_CSV_FILE = new File("files/cars.csv");
    private static final File GARAGES_CSV_FILE = new File("files/garages.csv");
    private static DbGarage instance;

    private DbGarage() {
    }

    public static DbGarage getInstance() {
        if (instance == null) {
            instance = new DbGarage();
        }
        try {
            createDir("files");
            createFile(CARS_CSV_FILE.getAbsolutePath());
            createFile(GARAGES_CSV_FILE.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private static void createDir(String path)  {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private static void createFile(String path) throws IOException {
        File file = new File(path).getAbsoluteFile();
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void updateCar(String id, Car newCar) {
        List<Car> cars = readCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getId().equals(id)) {
                cars.set(i, newCar);
                break;
            }
        }
        updateCarsCsv(cars);
    }

    public void updateGarage(String id, Garage newGarage) {
        List<Garage> garages = readGarages();
        for (int i = 0; i < garages.size(); i++) {
            Garage garage = garages.get(i);
            if (garage.getId().equals(id)) {
                garages.set(i, newGarage);
                break;
            }
        }
        updateGaragesCsv(garages);
    }

    private void updateCarsCsv(List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARS_CSV_FILE))) {
            for (Car car : cars) {
                String line = String.format("%s,%s,%s,%s,%s",
                        car.getCarBrand(),
                        car.getCarOwnerName(),
                        car.getCarSerialNumber(),
                        car.getGarageIdSet().stream()
                                .map(id -> id.replace(",", "\\,"))
                                .collect(Collectors.joining(";")),
                        car.getId());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating car in CSV file: " + e.getMessage());
        }
    }

    private void updateGaragesCsv(List<Garage> garages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GARAGES_CSV_FILE))) {
            for (Garage garage : garages) {
                String line = String.format("%s,%s,%s",
                        garage.getAddress(),
                        garage.getCarIdList().stream()
                                .map(id -> id.replace(",", "\\,"))
                                .collect(Collectors.joining(";")),
                        garage.getId());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating car in CSV file: " + e.getMessage());
        }
    }

    private void saveCars(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARS_CSV_FILE, true))) {
            String line = String.format("%s,%s,%s,%s,%s",
                    car.getCarBrand(),
                    car.getCarOwnerName(),
                    car.getCarSerialNumber(),
                    car.getGarageIdSet().stream().map(carId -> carId.replace(",", "\\,"))
                            .collect(Collectors.joining(";")),
                    car.getId());
            writer.append(line).append("\n");
        } catch (IOException e) {
            System.err.println("Error saving cars to CSV file: " + e.getMessage());
        }
    }

    private void saveGarages(Garage garage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GARAGES_CSV_FILE, true))) {
            String line = String.format("%s,%s,%s",
                    garage.getAddress(),
                    garage.getCarIdList().stream().map(garageId -> garageId.replace(",", "\\,"))
                            .collect(Collectors.joining(";")),
                    garage.getId());
            writer.append(line).append("\n");
        } catch (IOException e) {
            System.err.println("Error saving cars to CSV file: " + e.getMessage());
        }
    }

    private List<Car> readCars() {
        List<Car> cars = new ArrayList<>();
        try (Scanner scanner = new Scanner(CARS_CSV_FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Car car = new Car();
                car.setCarBrand(values[0]);
                car.setCarOwnerName(values[1]);
                car.setCarSerialNumber(values[2]);
                car.setGarageIdSet(new HashSet<>(Arrays.asList(values[3].split(";"))));
                car.setId(values[4]);
                cars.add(car);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private List<Garage> readGarages() {
        List<Garage> garages = new ArrayList<>();
        try (Scanner scanner = new Scanner(GARAGES_CSV_FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Garage garage = new Garage();
                garage.setAddress(values[0]);
                garage.setCarIdSet(new HashSet<>(Arrays.asList(values[1].split(";"))));
                garage.setId(values[2]);
                garages.add(garage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return garages;
    }

    private String generateCarId() {
        String id = RandomStringUtils.randomNumeric(4);
        if (readCars().stream().anyMatch(car -> car.getId().equals(id))) {
            return generateCarId();
        }
        return id;
    }

    private String generateGarageId() {
        String id = RandomStringUtils.randomNumeric(4);
        if (readGarages().stream().anyMatch(garage -> garage.getId().equals(id))) {
            return generateGarageId();
        }
        return id;
    }

    public void addCar(Car car) {
        car.setId(generateCarId());
        saveCars(car);
    }

    public void addGarage(Garage garage) {
        garage.setId(generateGarageId());
        saveGarages(garage);
    }

    public Optional<Car> getCar(String id) {
        return readCars()
                .stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    public Optional<Garage> getGarage(String id) {
        return readGarages()
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
            for (Garage garage_i : readGarages()) {
                if (garage_i.getCarIdList().contains(carId)) {
                    System.out.println("This car already in another garage");
                    return;
                }
                car.getGarageIdSet().add(garageId);
                garage.getCarIdList().add(carId);
                updateCar(carId, car);
                updateGarage(garageId, garage);
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
        List<Car> cars = readCars();
        List<Garage> garages = readGarages();
        cars.removeIf(car -> car.getId().equals(id));
        garages.stream().map(Garage::getCarIdList).forEach(car -> car.remove(id));
        updateCarsCsv(cars);
        updateGaragesCsv(garages);
    }

    public void deleteCarFromGarage(String carId, String garageId) {
        Optional<Car> optionalCar = getCar(carId);
        Optional<Garage> optionalGarage = getGarage(garageId);
        if (optionalCar.isPresent() && optionalGarage.isPresent()) {
            Garage garage = optionalGarage.get();
            Car car = optionalCar.get();
            garage.getCarIdList().remove(carId);
            car.getGarageIdSet().remove(garageId);
            updateCar(carId, car);
            updateGarage(garageId, garage);
        }
    }

    public void deleteGarage(String id) {
        List<Car> cars = readCars();
        List<Garage> garages = readGarages();
        cars.stream().map(Car::getGarageIdSet).forEach(garage -> garage.remove(id));
        garages.removeIf(garage -> garage.getId().equals(id));
        updateCarsCsv(cars);
        updateGaragesCsv(garages);
    }

    public void updateCar(String carId, String carOwnerName) {
        Optional<Car> optionalCar = getCar(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setCarOwnerName(carOwnerName);
            updateCar(carId, car);
        }
    }

    public List<Car> findAllCars() {
        return readCars();
    }

    public List<Garage> findAllGarages() {
        return readGarages();
    }
}