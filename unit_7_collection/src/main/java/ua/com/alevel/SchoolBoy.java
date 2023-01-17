package ua.com.alevel;

public class SchoolBoy extends Student {

    public SchoolBoy(String firstName, String lastName, Integer age) {
        super(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "SchoolBoy{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", age=" + getAge() +
                "}";
    }
}
