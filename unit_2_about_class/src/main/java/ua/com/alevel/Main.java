package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        Human human123 = new Human();
        System.out.println(human123.info());
        human123.name = "Alex";
        human123.age = 20;
        System.out.println(human123.info());
    }
}
