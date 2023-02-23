package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;
public class Garage extends BaseEntity {
    private String address;
    private Set<String> carIdSet = new HashSet<>();
    public void setAddress(String address) {
        this.address = address;
    }
    public Set<String> getCarIdList() {
        return carIdSet;
    }

    public String getAddress() {
        return address;
    }

    public Set<String> getCarIdSet() {
        return carIdSet;
    }

    public void setCarIdSet(Set<String> carIdSet) {
        this.carIdSet = carIdSet;
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