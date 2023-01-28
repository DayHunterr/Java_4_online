package ua.com.alevel;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

    public static final Set<Student> hashSet = new HashSet<>();
    public static final Set<Student> linkedHashSet = new LinkedHashSet<>();
    public static final Set<Student> treeSet = new TreeSet<>();

    public static void test() {
        hashSet.add(new Student("Ivan", "Ivanov", 24));
        hashSet.add(new Student("Ivan", "Ivanenko", 34));
        hashSet.add(new Student("Ivan", "Ivanenko", 35));
        hashSet.add(new Student("Petro", "Petrov", 36));
        hashSet.add(new Student("Maksim", "Maksimov", 19));
        hashSet.add(new Student("Oleg", "Olegov", 57));
        hashSet.add(new Student("Marina", "Marinenko", 25));
        hashSet.forEach(student -> System.out.println("student = " + student));

        System.out.println();

        linkedHashSet.add(new Student("Ivan", "Ivanenko", 34));
        linkedHashSet.add(new Student("Ivan", "Ivanov", 24));
        linkedHashSet.add(new Student("Maksim", "Maksimov", 19));
        linkedHashSet.add(new Student("Ivan", "Ivanenko", 35));
        linkedHashSet.add(new Student("Oleg", "Olegov", 57));
        linkedHashSet.add(new Student("Petro", "Petrov", 36));
        linkedHashSet.add(new Student("Marina", "Marinenko", 25));
        linkedHashSet.forEach(student -> System.out.println("student = " + student));

        System.out.println();

        treeSet.add(new Student("Ivan", "Ivanenko", 34));
        treeSet.add(new Student("Ivan", "Ivanov", 24));
        treeSet.add(new Student("Maksim", "Maksimov", 19));
        treeSet.add(new Student("Ivan", "Ivanenko", 35));
        treeSet.add(new Student("Oleg", "Olegov", 57));
        treeSet.add(new Student("Petro", "Petrov", 36));
        treeSet.add(new Student("Marina", "Marinenko", 25));
        treeSet.forEach(student -> System.out.println("student = " + student));
    }
}
