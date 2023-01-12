package ua.com.alevel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {

    private static final Map<Integer, String> hashMap = new HashMap<>();
    private static final Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
    private static final Map<Integer, String> treeMap = new TreeMap<>();

    public static void test() {
        put();
    }

    private static void put() {
//        Map<Student, String> studentStringMap = new HashMap<>();
//        studentStringMap.put(new Student("Ivan", "Ivanov"), "test1");
//        studentStringMap.put(new Student("Petro", "Petrov"), "test2");
//        studentStringMap.put(new Student("Maksim", "Maksimov"), "test3");
//        studentStringMap.put(new Student("Oleg", "Olegov"), "test4");
//        studentStringMap.put(new Student("Marina", "Marinenko"), "test5");
//        studentStringMap.forEach((k, v) -> System.out.println("k = " + k));
//
//        System.out.println();
//
//        Map<Student, String> studentStringLinkedMap = new LinkedHashMap<>();
//        studentStringLinkedMap.put(new Student("Ivan", "Ivanov"), "test1");
//        studentStringLinkedMap.put(new Student("Petro", "Petrov"), "test2");
//        studentStringLinkedMap.put(new Student("Maksim", "Maksimov"), "test3");
//        studentStringLinkedMap.put(new Student("Oleg", "Olegov"), "test4");
//        studentStringLinkedMap.put(new Student("Marina", "Marinenko"), "test5");
//        studentStringLinkedMap.forEach((k, v) -> System.out.println("k = " + k));

//        treeMap.put(3, "test1");
//        treeMap.put(5, "test2");
//        treeMap.put(99, "test3");
//        treeMap.put(2, "test4");
//        treeMap.put(0, "test5");
//        treeMap.forEach((k, v) -> {
//            System.out.println("k = " + k);
//            System.out.println("v = " + v);
//        });

        Map<Student, String> studentStringTreeMap = new TreeMap<>();
        studentStringTreeMap.put(new Student("Ivan", "Ivanov", 24), "test1");
        studentStringTreeMap.put(new Student("Ivan", "Ivanenko", 34), "test1");
        studentStringTreeMap.put(new Student("Ivan", "Ivanenko", 35), "test1");
        studentStringTreeMap.put(new Student("Petro", "Petrov", 36), "test2");
        studentStringTreeMap.put(new Student("Maksim", "Maksimov", 19), "test3");
        studentStringTreeMap.put(new Student("Oleg", "Olegov", 57), "test4");
        studentStringTreeMap.put(new Student("Marina", "Marinenko", 25), "test5");
        studentStringTreeMap.forEach((k, v) -> System.out.println("k = " + k));

        studentStringTreeMap.keySet();

        studentStringTreeMap.get(new Student("Petro", "Petrov", 36));
    }
}
