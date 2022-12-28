package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
        System.out.println("If you want create user, please enter 1");
        System.out.println("If you want find user, please enter 2");
        System.out.println("If you want delete user, please enter 3");
        System.out.println("If you want find all users, please enter 4");
        System.out.println("If you want close application, please enter 5");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findByEmail(reader); break;
            case "3" : deleteByEmail(reader); break;
            case "4" : findAll(); break;
            case "5" : stop(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create user");
        System.out.println("Please enter name");
        String name = reader.readLine();
        System.out.println("Please enter email");
        String email = reader.readLine();
        System.out.println("Please enter telegram");
        String telegram = reader.readLine();
        System.out.println("Please enter git hub");
        String gitHub = reader.readLine();
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setTelegramAcc(telegram);
        student.setGitHubAcc(gitHub);
        StudentStorage.addStudent(student);
    }

    private void findByEmail(BufferedReader reader) throws IOException {
        System.out.println("Find user by email");
        String email = reader.readLine();
        Student student = StudentStorage.getStudent(email);
        System.out.println("student = " + student);
    }

    private void deleteByEmail(BufferedReader reader) throws IOException {
        System.out.println("Delete user by email");
        String email = reader.readLine();
        StudentStorage.deleteStudent(email);
    }

    private void findAll() {
        System.out.println("Find all users");
        List<Student> students = StudentStorage.getStudents();
        System.out.println("students = " + students);
    }

    private void stop() {
        System.exit(0);
    }
}
