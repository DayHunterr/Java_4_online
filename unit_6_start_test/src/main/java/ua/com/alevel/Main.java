package ua.com.alevel;

import ua.com.alevel.config.StudentFactory;
import ua.com.alevel.controller.StudentController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new StudentFactory().configure();
        new StudentController().start();
    }
}
