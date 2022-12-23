package ua.com.alevel;

public class Human {

    int a = 10 ;
    int b = 5;
    int c = 4;

    int d = a + b + c;

    byte p = 1;

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
