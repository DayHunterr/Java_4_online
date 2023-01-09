package ua.com.alevel;

public class AnonimTest {

    public static void test() {
        FInterfaceImpl fInterface = new FInterfaceImpl();
        int sum = fInterface.sum(2,8);
        System.out.println("sum = " + sum);

        FInterface fInterface1 = new FInterfaceImpl();
        sum = fInterface1.sum(3,9);
        System.out.println("sum = " + sum);

        FInterface fInterface2 = new FInterface() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };

        FInterface fInterface3 = (a, b) -> a + b;
        sum = fInterface3.sum(4, 7);
        System.out.println("sum = " + sum);

        FInterface fInterface4 = Integer::sum;
    }

    private int getFInterface(int a, int b) {
        FInterface fInterface = new FInterface() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };
        return fInterface.sum(a, b);
    }
}
