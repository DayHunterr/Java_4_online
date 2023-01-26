package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

//        Runtime runtime = Runtime.getRuntime();
//        runtime.freeMemory();
//        System.out.println("runtime = " + runtime.totalMemory());
//        System.out.println("runtime = " + runtime.freeMemory());
//        Throwable throwable;
//
//        Error error; // unchecked
//        Exception exception; // checked
//
//        IOException ioException;
//        SQLException sqlException;
//        RuntimeException runtimeException;
//        test(10, 0);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String a = bufferedReader.readLine();
//            String b = bufferedReader.readLine();
////            System.exit(0);
//            int left = Integer.parseInt(a);
//            int right = Integer.parseInt(b);
//            int result = div2(left, right);
//            System.out.println("result = " + result);
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("wrong format");
//        } catch (IOException e) {
//            throw new RuntimeException("io problem");
//        } catch (Exception e) {
//            throw new CustomException("CustomException: no operation divider");
//        } finally {
//            System.out.println("finally");
//            bufferedReader.close();
//        }

//        try {
//
//        } finally {
//
//        }

        List<ExceptionThreadTest> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ExceptionThreadTest("thread_" + i));
        }

        for (ExceptionThreadTest exceptionThreadTest : list) {
            try {
                exceptionThreadTest.start();
                String name = exceptionThreadTest.getName();
                System.out.println("name = " + name);
            } catch (RuntimeException re) {
                System.out.println("re = " + re.getMessage());
            }
        }
    }

    public static int div2(int a, int b) throws Exception {
        return a / b;
    }

    public static void test(int a, int b) {
        try {
            int d2 = div2(a, b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        try {
//            int d = div2(a, b);
//            System.out.println("write d into file: " + d);
//        } catch (RuntimeException runtimeException) {
//            System.out.println("runtimeException = " + runtimeException);
//        }
    }

    public static int div1(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("we have a problem: incorrect value zero");
        }
        return a / b;
    }


    public void test() {
        test1(1, 7, 0, 67);
    }

    public void test1(int ... ints) {
        for (int anInt : ints) {

        }
    }

//    public void createObject(Class<? extends Object> aClass) {
//        aClass.getDeclaredConstructor().newInstance();
//    }
}
