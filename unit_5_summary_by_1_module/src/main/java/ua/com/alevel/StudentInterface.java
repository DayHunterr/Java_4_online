package ua.com.alevel;

import ua.com.alevel.db.DbStorage;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ua.com.alevel.db.DbStorage.findAllCourses;

public class StudentInterface {

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create student, please enter 1");
        System.out.println("If you want find student, please enter 2");
        System.out.println("If you want find all students, please enter 3");
        System.out.println("If you want create course, please enter 4");
        System.out.println("If you want find course, please enter 5");
        System.out.println("If you want find all courses, please enter 6");
        System.out.println("If you want attach student to course, please enter 7");
        System.out.println("If you want find students by course, please enter 8");
        System.out.println("If you want close application, please enter 9");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findByid(reader); break;
            case "3" : findAll(); break;
            case "4" : createCourse(reader); break;
            case "5" : findCourseByid(reader); break;
            case "6" : findAllCourses(); break;
            case "7" : attachStudentToCourse(reader); break;
            case "8" : findStudentByCourse(reader); break;
            case "9" : stop(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create user");
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        DbStorage.addStudent(student);
    }

    private void createCourse(BufferedReader reader) throws IOException {
        System.out.println("Create course");
        System.out.println("Please enter name");
        String name = reader.readLine();
        Course course = new Course();
        course.setName(name);
        DbStorage.addCourse(course);
    }

    private void findByid(BufferedReader reader) throws IOException {
        System.out.println("Find user by id");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Student student = DbStorage.getStudent(id);
        System.out.println("student = " + student);
    }

    private void findCourseByid(BufferedReader reader) throws IOException {
        System.out.println("Find course by id");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Course course = DbStorage.getCourse(id);
        System.out.println("course = " + course);
    }

    private void attachStudentToCourse(BufferedReader reader) throws IOException {
        System.out.println("Attach student to course");
        System.out.println("Please enter course id");
        String courseId = reader.readLine();
        System.out.println("Please enter student id");
        String studentId = reader.readLine();
        DbStorage.addStudentToCourse(studentId, courseId);
    }

    private void findStudentByCourse(BufferedReader reader) throws IOException {
        System.out.println("find all students by course");
        System.out.println("Please enter course id");
        String courseId = reader.readLine();
        List<Student> students = DbStorage.findByCourse(courseId);
        System.out.println("students = " + students);
    }

    private void findAll() {
        System.out.println("Find all users");
        List<Student> students = DbStorage.findAllStudents();
        System.out.println("students = " + students);
    }

    private void findAllCourses() {
        System.out.println("courses = " + DbStorage.findAllCourses());
    }

    private void stop() {
        System.exit(0);
    }
}
