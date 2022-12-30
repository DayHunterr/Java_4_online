package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DbStudent {

    private List<Student> students = new ArrayList<>();
    private static DbStudent instance;

    private DbStudent() { }

    public static DbStudent getInstance() {
        if (instance == null) {
            instance = new DbStudent();
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

    public boolean existByEmail(String email) {
        return students.stream().anyMatch(student -> student.getEmail().equals(email));
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(student -> student.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
