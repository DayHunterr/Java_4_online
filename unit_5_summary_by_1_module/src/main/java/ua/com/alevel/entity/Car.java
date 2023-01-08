package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Car extends BaseEntity {

    private String carBrand;
    private String carOwnerName;
    private String carSerialNumber;
    private Set<String> garageIdSet = new HashSet<>();

    public String getCarBrand() {
        return carBrand;
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

    public String getCarSerialNumber() {
        return carSerialNumber;
    }

    public void setCarSerialNumber(String carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
    }

    public Set<String> getGarageIdList() {
        return garageIdSet;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carOwnerName='" + carOwnerName + '\'' +
                ", carSerialNumber='" + carSerialNumber + '\'' +
                ", garageIdList=" + garageIdSet +
                ", id=" + getId() +
                '}';
    }
}
