package ua.com.alevel;

public class StudentDto {

    private final String id;
    private final String name;
    private final int age;

    public StudentDto(StudentEntity studentEntity) {
        this.id = studentEntity.id();
        this.name = studentEntity.name();
        this.age = studentEntity.age();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
