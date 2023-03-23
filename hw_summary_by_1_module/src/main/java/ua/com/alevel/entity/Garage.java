package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Garage extends BaseEntity {

    private String address;

    private Set<String> carIdSet = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  Set<String> getCarIdList() {
            return carIdSet;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "address='" + address + '\'' +
                ", carIdList=" + carIdSet +
                ", id=" + getId() +
                '}';
    }
}
