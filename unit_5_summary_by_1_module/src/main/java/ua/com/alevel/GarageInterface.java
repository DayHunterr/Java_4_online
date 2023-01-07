package ua.com.alevel;

import ua.com.alevel.db.GarageServiceStorage;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Garage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class GarageInterface {

        public void start() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Select your options");
            String select;
            menu();
            while ((select = reader.readLine()) != null) {
                crud(reader,select);
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
                case "1" : create(reader); break;
                case "2" : findCarById(reader); break;
                case "3" : findAllCars(); break;
                case "4" : updateCar(reader); break;
                case "5" : deleteCar(reader); break;
                case "6" : createGarage(reader); break;
                case "7" : findGarageById(reader); break;
                case "8" : findAllGarages(); break;
                case "9" : putCarToGarage(reader); break;
                case "10" : changeGarageForCar(reader); break;
                case "11" : deleteCarFromGarages(reader); break;
                case "12" : deleteGarage(reader); break;
                case "13" : findCarByGarage(reader); break;
                case "14" : stop(); break;
            }
            menu();
        }

        private void create(BufferedReader reader) throws IOException {
            System.out.println("Create car");
            Car car = new Car();
          validateCarBrand(reader,car);
          validateCarOwnerName(reader,car);
          validateCarSerialNumber(reader,car);
          GarageServiceStorage.addCar(car);
        }

        private void createGarage(BufferedReader reader) throws IOException {
            System.out.println("Create garage");
            Garage garage = new Garage();
            validateGarageAddress(reader,garage);
            GarageServiceStorage.addGarage(garage);
        }

        private void findCarById(BufferedReader reader) throws IOException {
            System.out.println("Find car by id");
            System.out.println("Please enter id");
            String id = reader.readLine();
            Car car = GarageServiceStorage.getCar(id);
            if(car == null)
            {
                System.out.println("There are nothing to find");

            } else {

                System.out.println("Car = " + car);
            }

        }

        private void findGarageById(BufferedReader reader) throws IOException {
            System.out.println("Find garage by id");
            System.out.println("Please enter garage id");
            String id = reader.readLine();
            Garage garage = GarageServiceStorage.getGarage(id);
            if( garage == null)
            {
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
            if(GarageServiceStorage.getCar(carId) != null && GarageServiceStorage.getGarage(garageId) != null)
            {
                GarageServiceStorage.addCarToGarage(carId,garageId);
            }else {
                System.out.println("This ids does not exist");
            }

        }

        private void findCarByGarage(BufferedReader reader) throws IOException {
            System.out.println("Find all cars by garage");
            System.out.println("Please enter garage id");
            String garageId = reader.readLine();
            List<Car> cars = GarageServiceStorage.findByGarage(garageId);
            if(cars.isEmpty())
            {
                System.out.println("There are nothing to find!");

            } else
            {
                cars.forEach(System.out::println);
            }

        }

        private void findAllCars() {
            System.out.println("Find all cars");
            List<Car> cars = GarageServiceStorage.findAllCars();

                if(cars.isEmpty())
                {
                    System.out.println("There are nothing to find!");


                } else
                {
                    cars.forEach(System.out::println);

                }

        }

        private void findAllGarages() {
            System.out.println("Find all garages");
            List<Garage> garages = GarageServiceStorage.findAllGarages();

            if(garages.isEmpty())
            {
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
            if (GarageServiceStorage.getCar(carId) != null) {
                if(carOwnerName.matches("^[a-zA-Z]{3,15}$"))
                {
                    GarageServiceStorage.updateCar(carId, carOwnerName);
                } else System.out.println("Invalid input! use only letters!");
            } else System.out.println("This id does not exist");
            }




        private void deleteCar(BufferedReader reader) throws IOException {
            System.out.println("Delete car");
            System.out.println("Please enter car id which you want to delete");
            String carId = reader.readLine();

            if (GarageServiceStorage.getCar(carId) != null) {

                    GarageServiceStorage.deleteCar(carId);

            } else {
                System.out.println("This id does not exist");
            }
        }
        private void deleteGarage(BufferedReader reader) throws IOException
        {
            System.out.println("Delete Garage");
            System.out.println("Please enter garage id which you want to delete");
            String garageId = reader.readLine();
            if(GarageServiceStorage.getGarage(garageId) != null)
            {
                GarageServiceStorage.deleteGarage(garageId);
            } else {
                System.out.println("This id does not exist");
            }

        }

        private void deleteCarFromGarages(BufferedReader reader) throws IOException
        {
            System.out.println("Delete car from garage");
            System.out.println("Please enter car id car which you want to delete");
            String carId = reader.readLine();
            System.out.println("Please enter garage id from which you want to delete");
            String garageId = reader.readLine();
            if(GarageServiceStorage.getCar(carId) != null && GarageServiceStorage.getGarage(garageId) != null)
            {
                GarageServiceStorage.deleteCarFromGarage(carId, garageId);
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
                }
                else {
                    System.out.println("Wrong input! Use only letters ");

                }
            }
        }

    private void validateCarOwnerName(BufferedReader reader, Car car) throws IOException
    {
        String carOwnerName;
        while (true)
        {
            System.out.println("Please enter car owner name");
            carOwnerName = reader.readLine();
            if(carOwnerName.matches("^[a-zA-Z]{3,15}$")){
                car.setCarOwnerName(carOwnerName);
                break;

            } else {
                System.out.println("Wrong input! Use only letters ");
            }
        }
    }
    private void validateCarSerialNumber(BufferedReader reader, Car car) throws  IOException
    {
        String carSerialNumber;
        while (true)
        {
            System.out.println("Please enter your car serial number format <XX1234XX> ");
            carSerialNumber = reader.readLine();
            if(carSerialNumber.matches("^[A-Z]{2}[0-9]{4}[A-Z]{2}$"))
            {
                car.setCarSerialNumber(carSerialNumber);
                break;

            } else
            {
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
            }
            else {
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
        if(GarageServiceStorage.getCar(carNum) != null && GarageServiceStorage.getGarage(oldGarageNum) != null && GarageServiceStorage.getGarage(garageNum) != null)
        {
            GarageServiceStorage.deleteCarFromGarage(carNum,oldGarageNum);
            GarageServiceStorage.addCarToGarage(carNum,garageNum);
        } else
        {
            System.out.println("This ids does not exist");
        }


    }

        private void stop() {
            System.exit(0);
        }
    }




