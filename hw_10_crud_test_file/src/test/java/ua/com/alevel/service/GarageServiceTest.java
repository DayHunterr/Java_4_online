package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GarageServiceTest {

    private static final GarageService garageService;

    static {
        garageService = new GarageService();
    }

    private static final String CARS_CSV_FILE = "dicks/cars.csv";
    private static final String GARAGES_CSV_FILE = "dicks/garages.csv";
    private static final String TEST_DIR = "dicks/";
    private static final int CARS = 10;
    private static final int GARAGES = 5;
    private static final String CARBRAND = "testBrand";
    private static final String CAROWNERNAME = "testCarOwnerName";
    private static final String CARSERIALNUMBER = "XX1234FF";
    private static final String GARAGEADDRESS = "testGarageAddress";

    @BeforeAll
    public static void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.createFile(Paths.get(CARS_CSV_FILE));
        Files.createFile(Paths.get(GARAGES_CSV_FILE));

        for (int i = 0; i < CARS; i++) {
            Car car = generateCar(i);
            garageService.addCar(car);
        }
        for (int g = 0; g < GARAGES; g++){
            Garage garage = generateGarage(g);
            garageService.addGarage(garage);
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(CARS_CSV_FILE));
        Files.deleteIfExists(Paths.get(GARAGES_CSV_FILE));
        Files.deleteIfExists(Paths.get(TEST_DIR));
    }

    @Test
    @Order(1)
    public void checkCreatedCars() {
        Assertions.assertEquals(garageService.findAllCars().size(), CARS);
    }

    @Test
    @Order(2)
    public void checkCreatedGarages(){
        Assertions.assertEquals(garageService.findAllGarages().size(),GARAGES);
    }

    @Test
    @Order(3)
    public void createCar() {
        Car car = generateCar(CARS + 1);
        garageService.addCar(car);
        Assertions.assertEquals(garageService.findAllCars().size(), CARS + 1);
    }

    @Test
    @Order(4)
    public void createGarage() {
        Garage garage = generateGarage(GARAGES + 1);
        garageService.addGarage(garage);
        Assertions.assertEquals(garageService.findAllGarages().size(), GARAGES + 1);
    }

    @Test
    @Order(5)
    public void checkIsGetCar(){
        String id = garageService.findAllCars().get(garageService.findAllCars().size()-1).getId() ;
        Optional<Car> car = garageService.getCar(id);
        Assertions.assertTrue(car.isPresent());
    }

    @Test
    @Order(6)
    public void checkIsGetGarage(){
        String id = garageService.findAllGarages().get(garageService.findAllGarages().size()-1).getId() ;
        Optional<Garage> garage = garageService.getGarage(id);
        Assertions.assertTrue(garage.isPresent());
    }

    @Test
    @Order(7)
    public void addCarToGarage(){
        String carId = garageService.findAllCars().get(garageService.findAllCars().size()-1).getId();
        String garageId = garageService.findAllGarages().get(garageService.findAllGarages().size()-1).getId();
        garageService.addCarToGarage(carId,garageId);
        Set<String> garageSet = garageService.getGarage(garageId).get().getCarIdList();
        Assertions.assertTrue(garageSet.contains(carId));
    }

    @Test
    @Order(8)
    public void findByGarage(){
        String garageId = garageService.findAllGarages().get(garageService.findAllGarages().size()-1).getId();
        List<Car> carList = garageService.findByGarage(garageId);
        Assertions.assertFalse(carList.isEmpty());
    }

    @Test
    @Order(9)
    public void deleteCar(){
        Car car = garageService.findAllCars().get(0);
        garageService.deleteCar(car.getId());
        Assertions.assertEquals(garageService.findAllCars().size(),CARS);
    }

    @Test
    @Order(10)
    public void deleteCarFromGarage(){
        String garageId = garageService.findAllGarages().get(garageService.findAllGarages().size()-1).getId();
        String carId = garageService.findAllCars().get(garageService.findAllCars().size()-1).getId();
        garageService.deleteCarFromGarage(carId,garageId);
        Optional<Garage> garage = garageService.getGarage(garageId);
        Assertions.assertFalse(garage.get().getCarIdList().contains(carId));
    }

    @Test
    @Order(11)
    public void deleteGarage(){
        Garage garage = garageService.findAllGarages().get(0);
        garageService.deleteGarage(garage.getId());
        Assertions.assertEquals(garageService.findAllGarages().size(),GARAGES);
    }

    @Test
    @Order(12)
    public void updateCar(){
        String newCarOwnerName = "Jane Doe";
        String carId = garageService.findAllCars().get(garageService.findAllCars().size()-1).getId();
        garageService.updateCar(carId, newCarOwnerName);
        Optional<Car> optionalCar = garageService.getCar(carId);
        Assertions.assertEquals(newCarOwnerName,optionalCar.get().getCarOwnerName());
    }

    private static Car generateCar(int i){
        Car car = new Car();
        car.setCarBrand(CARBRAND + i);
        car.setCarOwnerName(CAROWNERNAME + i);
        car.setCarSerialNumber(CARSERIALNUMBER + i);
        return car;
    }

    private  static Garage generateGarage(int g){
        Garage garage = new Garage();
        garage.setAddress(GARAGEADDRESS + g);
        return garage;
    }
}