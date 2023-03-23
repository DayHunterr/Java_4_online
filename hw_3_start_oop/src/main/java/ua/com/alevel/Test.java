package ua.com.alevel;

public class Test {

    String name = "";
    boolean isStupid = false;

    void sendMessage(String message, int count) {
        if (count == 6) {
            System.out.println("message = " + message + " is done");
            return;
        }
        if (isStupid) {
            return;
        }

    }
}
