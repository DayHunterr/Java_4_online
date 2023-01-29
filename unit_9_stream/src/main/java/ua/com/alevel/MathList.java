package ua.com.alevel;

public class MathList {

    public MathList() { }

    public MathList mathList() {
        return new MathList();
    }

    public static int staticSum(int a, int b) {
        return a + b;
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
