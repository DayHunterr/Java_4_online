package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;
public class Garage extends BaseEntity {
    private String address;
    public void setAddress(String address) {
        this.address = address;
    }
    public Garage(){
        super();
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "address='" + address + '\'' +
                ", id=" + getId() +
                '}';
    }
}