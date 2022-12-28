package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Student extends BaseEntity {

    private String firstName;
    private String lastName;
    private Set<String> courseIdList = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getCourseIdList() {
        return courseIdList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + getId() + '\'' +
                ", courseIdList=" + courseIdList +
                '}';
    }
}
