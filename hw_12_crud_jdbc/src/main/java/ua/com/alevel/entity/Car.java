package ua.com.alevel.entity;


public class Car extends BaseEntity {

    private String carBrand;
    private String carOwnerName;
    private String carSerialNumber;

    public Car() {
        super();
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarOwnerName() {
        return carOwnerName;
    }

    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public void setCarSerialNumber(String carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarSerialNumber() {
        return carSerialNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carOwnerName='" + carOwnerName + '\'' +
                ", carSerialNumber='" + carSerialNumber + '\'' +
                ", id=" + getId() +
                '}';
    }
}