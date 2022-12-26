package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DbStudent2 {

    private List<Student> students = new ArrayList<>();
    private static DbStudent2 instance;

    private DbStudent2() { }

    public static DbStudent2 getInstance() {
        if (instance == null) {
            instance = new DbStudent2();
        }
        return instance;
    }

    public void create(Student student) {
        student.setId(generateId());
        students.add(student);
    }

    public void update(Student student) {
        Optional<Student> optionalStudent = findById(student.getId());
        if (optionalStudent.isPresent()) {
            Student current = optionalStudent.get();
            current = student;
        }
    }

    public void delete(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> findById(String id) {
        return students
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(student -> student.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
