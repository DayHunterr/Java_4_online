package ua.com.alevel;

public class Human {

    String name;
    int age;
    long salary;

    boolean isMan = true;

//    Human()
//    {
//        name = "Ivan";
//        age = 30;
//    }

    void drink()
    {

    }

    String info() {
        return "name = " + name + ", age = " + age + ", salary = " + salary + ", isMan = " + isMan;
    }
}
