package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //1
//        List<Student> students = StudentStorage.getStudents();
//        System.out.println("students = " + students);
//
//        Student student = new Student();
//        student.setName("Alisa Sira");
//        student.setEmail("alisa.sira@gmail.com");
//        student.setGitHubAcc("https://github.com/alisasira");
//        student.setTelegramAcc("https://t.me/AlisaSira");
//
//        students.add(student);
//        System.out.println("students = " + students);
//
//        Student student1 = new Student();
//        student1.setName("Oksana Vizitiv");
//        student1.setEmail("sem4ykk@gmail.com");
//        student1.setGitHubAcc("https://github.com/inspirrrada");
//        student1.setTelegramAcc("https://t.me/inspirrrada");
//
//        students.add(student1);
//        System.out.println("students = " + students);
//
//        Student getStudent = StudentStorage.getStudent("sem4ykk@gmail.com");
//
//        System.out.println("getStudent = " + getStudent);


//        student.name = "7985389563204562304";

        StudentInterface studentInterface = new StudentInterface();
        studentInterface.start();
    }
}
