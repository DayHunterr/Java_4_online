package ua.com.alevel;

import java.util.stream.Stream;

public class DoubleDot {

    private final Stream<Integer> integerStream2 = Stream.of(1,1,1,1 ,3, 9, 4, 5, 90, 12, 12, 27);

    public DoubleDot() { }

    public void run() {
//        v1();
//        v2();
        v3();
    }

    private void v1() {
//        int sum = integerStream2.reduce(0, (a, b) -> a + b);
//        int sum = integerStream2.reduce(0, (a, b) -> this.sum(a, b));
        int sum = integerStream2.reduce(0, this::sum);
        System.out.println("sum = " + sum);
    }

    private void v2() {
//        int sum = integerStream2.reduce(0, (a, b) -> a + b);
//        int sum = integerStream2.reduce(0, (a, b) -> this.sum(a, b));
        int sum = integerStream2.reduce(0, MathList::staticSum);
        System.out.println("sum = " + sum);
    }

    private void v3() {
//        int sum = integerStream2.reduce(0, (a, b) -> a + b);
//        int sum = integerStream2.reduce(0, (a, b) -> this.sum(a, b));
        MathList mathList = new MathList();
//        int sum = integerStream2.reduce(0, new MathList()::sum);
        int sum = integerStream2.reduce(0, mathList::sum);
        System.out.println("sum = " + sum);
    }

    private int sum(int a, int b) {
        return a + b;
    }
}
