package ua.com.alevel.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "carBrand")
    private String carBrand;
    @Column(name = "carOwnerName")

    private String carOwnerName;
    @Column(name = "carSerialNumber")

    private String carSerialNumber;
    @ManyToMany(mappedBy = "cars", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Garage> garages;

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
