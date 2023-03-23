package ua.com.alevel;
public class Human {

    private String name;
    private int age;
    private String message;
    private boolean isFool;


    public Human(String name, int age,  String message, boolean isFool) {

        this.name = name;
        this.age = age;
        this.message = message;
        this.isFool = isFool;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFool() {
        return isFool;
    }

    public void setFool(boolean fool) {
        isFool = fool;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", message='" + message + '\'' +
                ", isFool=" + isFool +
                '}';
    }

}



