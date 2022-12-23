package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;

public class StudentStorage {

    private static List<Student> students = new ArrayList<>();
    private static Student[] studentsArray;

    private StudentStorage() { }

    public static List<Student> getStudents() {
        return students;
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void deleteStudent(String email) {
        students.removeIf(
                student -> student.getEmail().equals(email)
        );
    }

    public static Student getStudent(String email) {
        return students.
                stream().
                filter(student -> student.getEmail().equals(email)).
                findFirst()
                .get();
    }
}
