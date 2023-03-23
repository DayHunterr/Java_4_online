package ua.com.alevel;

import java.util.Random;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {
        MatList<Integer> mathList = new MatList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            mathList.add((i) * random.nextInt(10));
        }
        StringJoiner joiner = new StringJoiner(", ");
        mathList.forEach(e -> joiner.add(e.toString()));
        System.out.println(joiner);
    }
}
