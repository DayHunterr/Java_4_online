package ua.com.alevel;

import org.apache.commons.lang3.ObjectUtils;
import ua.com.alevel.db.DbStorage;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        int a = 10;
//        Student student = new Student();
//
//        String name = "Ivan";
//        if (name != null) {
//            System.out.println("not null");
//        } else if (name.length() < 100) {
//            System.out.println("less then 100");
//        } else if (name.length() < 100) {
//            System.out.println("less then 100");
//        } else if (name.length() < 100) {
//            System.out.println("less then 100");
//        } else if (name.length() < 100) {
//            System.out.println("less then 100");
//        } else {
//            System.out.println();
//        }
//
//        int count = 10;
//
//        if (count == 0) {
//            System.out.println();
//        }
//        if (count == 1) {
//            System.out.println();
//        }
//        if (count == 2) {
//            System.out.println();
//        }
//        if (count == 3) {
//            System.out.println();
//        }
//        if (count == 4) {
//            System.out.println();
//        }
//        if (count == 5) {
//            System.out.println();
//        }
//
//        switch (count) {
//            case 0 : {
//                System.out.println();
//            } break;
//            case 1 : {
//                System.out.println();
//            } break;
//            case 2 : {
//                System.out.println();
//            } break;
//            case 3 : {
//                System.out.println();
//            } break;
//            case 4 : {
//                System.out.println();
//            } break;
//            case 5, 6, 8 : {
//                System.out.println();
//            } break;
//            default: {
//                System.out.println();
//            }
////            case 6, 8 : {
////                System.out.println();
////            } break;
////            case 7 : {
////                System.out.println();
////            }break;
//        }
//
//        //17 version
//        switch (count) {
//            case 0,1,2 -> {}
//            case 3,4,5 -> {}
//            case 6 -> {}
//            case 7,8 -> {}
//        }
//
//        name = "Petro";
////        name = new String("Petro");
//        char[] chars = name.toCharArray();
//
//         name = new String(chars);
//        for (int i = 0; i < chars.length; i++) {
//            System.out.println("i = " + chars[i]);
//        }
//        for (char element : chars) { //foreach
//            System.out.println("element = " + element);
//        }
//        int position = 0;
//        while (position < chars.length) {
//            System.out.println("position = " + chars[position]);
//            ++position;
//        }
//
////        increment
//        int increment = 10;
//        System.out.println("increment = " + increment++);
//        System.out.println("increment = " + ++increment);
//        System.out.println("increment = " + --increment);
//
//        int[] arr = new int[10];
//        arr[0] = 1;
//
//        arr = new int[] { 0, 1, 2 };
//
//        int[][] arr1 = new int[3][];
//

//        Student student = new Student();
//        student.setFirstName("Iegor");
//        student.setLastName("Funtusov");
//        DbStorage.addStudent(student);
//        student = new Student();
//        student.setFirstName("Petro");
//        student.setLastName("Mogula");
//        DbStorage.addStudent(student);
//        System.out.println("students = " + DbStorage.findAllStudents());
//        Course course = new Course();
//        course.setName("JAVA");
//        DbStorage.addCourse(course);
//        System.out.println("courses = " + DbStorage.findAllCourses());

//        new StudentInterface().start();

        System.out.println(ObjectUtils.notEqual(1, 2));
    }
}
