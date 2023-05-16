package ua.com.alevel.exeption;

public class CustomException extends SecurityException{
    public CustomException(String text) {
        super(text);
    }
}
