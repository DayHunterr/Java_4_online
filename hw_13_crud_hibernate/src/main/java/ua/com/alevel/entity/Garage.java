package ua.com.alevel.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "garage")
public class Garage extends BaseEntity {
    @Column(name = "address")
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "car_in_gar",
            joinColumns = @JoinColumn(name = "gar_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars;

    public Garage() {
        super();
        cars = new HashSet<>();
    }

    @PreRemove
    private void onRemove() {
        if (!cars.isEmpty()) {
            throw new IllegalStateException("Garage is not Empty");
        }
    }

    public Set<Car> getCars() {
        return cars;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


    @Override
    public String toString() {
        return "Garage{" +
                "address='" + address + '\'' +
                ", id=" + getId() +
                '}';
    }
}
