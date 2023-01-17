package ua.com.alevel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ListTest {

    private static final int SIZE = 1_000_000;
    private static final int JIT_SIZE = 20;

    private static final List<Integer> arrayList = new ArrayList<>();
    private static final List<Integer> linkedList = new LinkedList<>();

    private static final Set<Integer> hashSet = new HashSet<>();
    private static final Set<Integer> linkedHashSet = new LinkedHashSet<>();
    private static final Set<Integer> treeSet = new TreeSet<>();

    public static void test() {
        add();
//        get();
//        delete();
        update();
    }

    private static void add() {
//        for (int j = 0; j < JIT_SIZE; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < SIZE; i++) {
                arrayList.add(i); // O(n), O(1)
            }
            long end = System.currentTimeMillis() - start;
            System.out.println("arrayList add finish = " + end);

            start = System.currentTimeMillis();
            for (int i = 0; i < SIZE; i++) {
                linkedList.add(i); // O(n), O(1)
            }
            end = System.currentTimeMillis() - start;
            System.out.println("linkedList add finish = " + end);
//        }
    }

    private static void get() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.get(i); // O(1)
        }
        for (Integer integer : arrayList) {

        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList get finish = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            linkedList.get(i); // O(n)
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList get finish = " + end);
    }

    private static void delete() {
        long start = System.currentTimeMillis();
//        for (int i = 0; i < SIZE; i++) {
//            arrayList.remove(i); // O(n)
//        }
        Iterator<Integer> iterator1 = arrayList.iterator();
        while (iterator1.hasNext()) {
            Integer i = iterator1.next();
            iterator1.remove();
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("remove arrayList finish = " + end);

        start = System.currentTimeMillis();
//        for (int i = 0; i < SIZE; i++) {
//            linkedList.remove(i); // O(1)
//        }
        Iterator<Integer> iterator2 = linkedList.iterator();
        while (iterator2.hasNext()) {
            Integer i = iterator2.next();
            iterator2.remove();
        }

        end = System.currentTimeMillis() - start;
        System.out.println("remove linkedList finish = " + end);
    }

    private static void update() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.set(i, i + 2); // O(1)
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList update finish = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            linkedList.set(i, i + 2); // O(n)
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList update finish = " + end);
    }
}
