package ua.com.alevel.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "carBrand")
    private String carBrand; // я раз 10 говорил, что в базе принять именовать car_brand

    @Column(name = "carOwnerName")
    private String carOwnerName; // я раз 10 говорил, что в базе принять именовать car_owner_name

    @Column(name = "carSerialNumber")
    private String carSerialNumber; // я раз 10 говорил, что в базе принять именовать car_serial_number

    @ManyToMany(mappedBy = "cars", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Garage> garages; // то есть ты не позволяешь пользователю получить список гаражей по кару?

    public Car() {
        super();
        garages = new HashSet<>();
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public void setCarSerialNumber(String carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
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
