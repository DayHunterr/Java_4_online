package ua.com.alevel;

import ua.com.alevel.constclass.Student1;
import ua.com.alevel.constclass.Student2;
import ua.com.alevel.en.Gender;
import ua.com.alevel.pac1.Pac1;
import ua.com.alevel.pac1.pac2.Pac2;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Student1 student1 = new Student1("some", "pass");
        String email = student1.getEmail();
        email = "some2";
        System.out.println("student1 = " + student1);

        Class<? extends Student1> student1Class = student1.getClass();
        for (Field declaredField : student1Class.getDeclaredFields()) {
            if (declaredField.getName().equals("email")) {
                declaredField.setAccessible(true);
                declaredField.set(student1, "some2");
            }
        }
        System.out.println("student1 = " + student1);

        Student2 student2 = new Student2("some", "pass");
        System.out.println("student2 = " + student2);
        System.out.println(student2.email());

//        Class<? extends Student2> student2Class = student2.getClass();
//        for (Field declaredField : student2Class.getDeclaredFields()) {
//            if (declaredField.getName().equals("email")) {
//                declaredField.setAccessible(true);
//                declaredField.set(student2, "some2");
//            }
//        }
    }
}
