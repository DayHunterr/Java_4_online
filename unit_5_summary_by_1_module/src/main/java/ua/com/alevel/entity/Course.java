package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Course extends BaseEntity {

    private String name;
    private Set<String> studentIdList = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getStudentIdList() {
        return studentIdList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", id=" + getId() +
                ", studentIdList=" + studentIdList +
                '}';
    }
}
