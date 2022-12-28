package ua.com.alevel.db;

import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DbStorage {

    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    private DbStorage() { }

    public static void addStudent(Student student) {
        student.setId(generateStudentId());
        students.add(student);
    }

    private static String generateStudentId() {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(student -> student.getId().equals(id))) {
            return generateStudentId();
        }
        return id;
    }

    private static String generateCourseId() {
        String id = UUID.randomUUID().toString();
        if (courses.stream().anyMatch(course -> course.getId().equals(id))) {
            return generateCourseId();
        }
        return id;
    }

    public static Student getStudent(String id) {
        return students.
                stream().
                filter(student -> student.getId().equals(id)).
                findFirst()
                .get();
    }

    public static List<Student> findAllStudents() {
        return students;
    }

    public static void addCourse(Course course) {
        course.setId(generateCourseId());
        courses.add(course);
    }

    public static Course getCourse(String id) {
        return courses.
                stream().
                filter(student -> student.getId().equals(id)).
                findFirst()
                .get();
    }

    public static List<Course> findAllCourses() {
        return courses;
    }

    public static void addStudentToCourse(String studentId, String courseId) {
        Course course = getCourse(courseId);
        Set<String> students = course.getStudentIdList();
        students.add(studentId);
    }

    public static List<Student> findByCourse(String courseId) {
        Course course = getCourse(courseId);
        Set<String> studentsIds = course.getStudentIdList();
        List<Student> students = new ArrayList<>();
        for (String studentId : studentsIds) {
            Student student = getStudent(studentId);
            if (student != null) {
                students.add(student);
            }
        }
        return students;
    }
}
