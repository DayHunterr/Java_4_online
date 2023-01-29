package ua.com.alevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.UUID;

public class SerialTest {

    private final Student student = generateStudent();

    public void test() throws IOException, ClassNotFoundException {
//        System.out.println("student = " + student);
        serial();
        deserial();
    }

    private void serial() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.out"));
        outputStream.writeObject(student);
        outputStream.flush();
    }

    private void deserial() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.out"));
        Object o = inputStream.readObject();
        Student s = (Student) o;
        System.out.println("s = " + s);
    }

    private Student generateStudent() {
        String id = UUID.randomUUID().toString();
        String firstName = UUID.randomUUID().toString();
        String lastName = UUID.randomUUID().toString();
        Student student1 = new Student();
        student1.setFirstName(firstName);
        student1.setLastName(lastName);
        student1.setFullName(firstName + " " + lastName);
        student1.setId(id);
        return student1;
    }
}
