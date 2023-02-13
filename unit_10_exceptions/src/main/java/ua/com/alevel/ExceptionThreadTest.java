package ua.com.alevel;

import java.util.Random;

public class ExceptionThreadTest extends Thread {

    public ExceptionThreadTest(String threadName) {
        super(threadName);
    }

    @Override
    public synchronized void start() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1) {
            System.out.println("i = " + i);
        } else {
            throw new RuntimeException(this.getName());
        }
    }
}
